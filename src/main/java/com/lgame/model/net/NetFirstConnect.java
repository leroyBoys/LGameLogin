package com.lgame.model.net;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

/**
 * Created by Administrator on 2017/5/13.
 */
public class NetFirstConnect{
    @Protobuf(fieldType = FieldType.INT32, order = 1, required = true)
    private int uid;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
