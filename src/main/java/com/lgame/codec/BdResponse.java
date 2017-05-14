package com.lgame.codec;

import com.google.protobuf.Message;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/14.
 */
public class BdResponse {
    private int module;
    private int cmd;
    private byte[] value;
    private Object baiduObj;
    private Message obj;
    private int seq = 0;
    private int status;
    private Map<Object, Object> attributeMap = new HashMap();

    public int getModule() {
        return module;
    }

    public void setModule(int module) {
        this.module = module;
    }

    public int getCmd() {
        return cmd;
    }

    public void setCmd(int cmd) {
        this.cmd = cmd;
    }

    public byte[] getValue() {
        return value;
    }

    public void setValue(byte[] value) {
        this.value = value;
    }

    public Message getObj() {
        return obj;
    }

    public void setObj(Message obj) {
        this.obj = obj;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public int getStatus() {
        return status;
    }

    public Object getBaiduObj() {
        return baiduObj;
    }

    public void setBaiduObj(Object baiduObj) {
        this.baiduObj = baiduObj;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Map<Object, Object> getAttributeMap() {
        return attributeMap;
    }

    public void setAttributeMap(Map<Object, Object> attributeMap) {
        this.attributeMap = attributeMap;
    }
}
