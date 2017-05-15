package com.lgame.codec;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import com.lgame.model.net.CmdEnum;
import com.lgame.util.encry.ZipTool;
import com.lsocket.codec.RequestDecoderClient;
import com.lsocket.manager.CMDManager;
import com.module.net.NetParentOld;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import java.io.IOException;

/**
 * Created by leroy:656515489@qq.com
 * 2017/4/6.
 */
public class RequestDecoderLocal extends RequestDecoderClient {

    protected boolean doDecode(IoSession session, IoBuffer input, ProtocolDecoderOutput out) throws Exception {
        int remainSize = input.remaining();
        if (remainSize > 0) {
            if (input.remaining() <= 4) {
                return true;
            }

            input.mark();// 标记当前位置，以便reset
            int size = input.getInt();// 读取前4字节

            if (size > input.remaining()) {// 如果消息内容不够，则重置，相当于不读取size
                input.reset();
                return false;
            }

            byte[] b = new byte[size];
            input.get(b);
            NetParentOld.NetCommond commond = NetParentOld.NetCommond.parseFrom(b);
            int cmd_c = commond.getCmd();
            int module = CMDManager.getModule(cmd_c);
            int cmd = CMDManager.getCmd(cmd_c);


            byte[] data = null;
            if(commond.getObj() != null){//秘钥验证

                data = commond.getObj().toByteArray();
                data = ZipTool.uncompressBytes(data);//解压缩
            }
          /*  Request request = cmdModule.getRequset(data,module,cmd,commond.getSeq());
            out.write(request);*/
            CmdEnum cmdEnum = CmdEnum.datas.get(cmd_c);
            if(cmdEnum == null){
                System.out.println("cmd:"+cmd+" module:"+module+" cant not find");
                return input.hasRemaining();
            }

            out.write(decode(data,cmdEnum.getRecCls()));
            return input.hasRemaining();
        }
        return false;
    }

    public static <T> T decode(byte[] datas, Class<?> t) throws IOException {
        Codec simpleTypeCode = ProtobufProxy.create(t);
        return (T) simpleTypeCode.decode(datas);
    }


}
