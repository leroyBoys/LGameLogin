package com.lgame.model.net;

/**
 * Created by Administrator on 2017/5/13.
 */
public class CmdMsg {
    private int uid;
    private int serverId;
    private int modlue;
    private int cmd;
    private String key;
    private String msg;

    public int getCmd() {
        return cmd;
    }

    public void setCmd(int cmd) {
        this.cmd = cmd;
    }

    public String getMsg() {
        return msg;
    }

    public String getKey() {
        return key;
    }

    public int getModlue() {
        return modlue;
    }

    public int getUid() {
        return uid;
    }

    public int getServerId() {
        return serverId;
    }

    public void setServerId(int serverId) {
        this.serverId = serverId;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setModlue(int modlue) {
        this.modlue = modlue;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
