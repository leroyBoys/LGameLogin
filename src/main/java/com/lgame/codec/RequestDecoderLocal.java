package com.lgame.codec;

import com.google.protobuf.InvalidProtocolBufferException;
import com.lsocket.codec.RequestDecoder;
import com.lsocket.module.Visitor;
import com.lsocket.util.DefaultSocketPackage;
import com.lsocket.util.ReceiveData;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

/**
 * Created by leroy:656515489@qq.com
 * 2017/4/6.
 */
public class RequestDecoderLocal extends RequestDecoder {

    @Override
    public void doRequeset(IoSession session, ProtocolDecoderOutput out,ReceiveData receiveData) throws InvalidProtocolBufferException {

    }
}
