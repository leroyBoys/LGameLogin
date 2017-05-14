package com.lgame.model.net;

/**
 * Created by Administrator on 2017/5/13.
 */
public class CmdMsg {
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
