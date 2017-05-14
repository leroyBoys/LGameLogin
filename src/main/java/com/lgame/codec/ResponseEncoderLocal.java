package com.lgame.codec;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import com.google.protobuf.ByteString;
import com.google.protobuf.Message;
import com.lgame.util.PrintTool;
import com.lgame.util.comm.Tools;
import com.lgame.util.encry.MD5Tool;
import com.lgame.util.encry.ZipTool;
import com.lsocket.codec.ResponseEncoderClient;
import com.lsocket.manager.CMDManager;
import com.module.net.NetParentOld;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import java.io.IOException;

/**
 * Created by leroy:656515489@qq.com
 * 2017/4/6.
 */
public class ResponseEncoderLocal extends ResponseEncoderClient {

    public void encode(IoSession session, Object message, ProtocolEncoderOutput encoderOutput) throws Exception {
        if (message == null) {
            return;
        }

        if ((message instanceof IoBuffer)) {
            encoderOutput.write(message);
            encoderOutput.flush();
            return;
        }

        try {

            byte[] t = getCommondMes((BdResponse) message,this.getClientServer().getKey(), session);
            // 定义一个发送消息协议格式：|--header:4 byte--|--content:10MB--|
            IoBuffer buf = transformByteArray(t);

            if (buf != null) {
                encoderOutput.write(buf);
                encoderOutput.flush();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public static IoBuffer transformByteArray(byte[] bytes) {
        if ((bytes == null) || (bytes.length == 0)) {
            return null;
        }
        int messageLength = bytes.length;
        IoBuffer buffer = IoBuffer.allocate(messageLength + 4);
        buffer.setAutoExpand(true);

        buffer.putInt(messageLength);
        buffer.put(bytes);
        buffer.flip();
        buffer.free();
        return buffer;
    }

    public static byte[] getCommondMes(BdResponse response,String key, IoSession session) throws Exception {
        NetParentOld.NetCommond.Builder com = NetParentOld.NetCommond.newBuilder();

        int cmdc = CMDManager.getCmd_M(response.getModule(),response.getCmd());
        com.setCmd(cmdc);
        com.setStatus(response.getStatus());
        int seq = response.getSeq();
        com.setSeq(seq);

        Message obj = response.getObj();

        byte[] datas = response.getValue();
        if(datas == null && obj != null){
            datas = obj.toByteArray();
            PrintTool.info("---Send---cmd:"+ CMDManager.getCmd(com.getCmd())+"  module:"+CMDManager.getModule(com.getCmd())+"  "+datas.toString());
        }else if(response.getBaiduObj() != null){
            datas = encode(response.getBaiduObj(),response.getBaiduObj().getClass());
        }else {
            PrintTool.info("---Send---cmd:"+ CMDManager.getCmd(com.getCmd())+"  module:"+CMDManager.getModule(com.getCmd()));
        }

        if(datas != null){
            byte[] data = ZipTool.compressBytes(datas);//压缩
            com.setObj(ByteString.copyFrom(data));

            String d = MD5Tool.GetMD5Code(Tools.getByteJoin(data, key.getBytes()));//加密
            com.setSn(d);
        }

        return com.build().toByteArray();
    }

    public static <T> T decode(byte[] datas, Class<?> t) throws IOException {
        Codec simpleTypeCode = ProtobufProxy.create(t);
        return (T) simpleTypeCode.decode(datas);
    }

    public static byte[] encode(Object obj, Class<?> t) throws IOException {
        Codec simpleTypeCode = ProtobufProxy.create(t);
        return simpleTypeCode.encode(obj);
    }
}
