package com.lgame.model;

/**
 * Created by Administrator on 2017/5/4.
 */
public class SELogin {
    private int uid;
    private String key;
    private String ipPort;
    public SELogin() {
    }

    public SELogin(int uid, String key, String ipPort) {
        this.uid = uid;
        this.key = key;
        this.ipPort = ipPort;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getIpPort() {
        return ipPort;
    }

    public void setIpPort(String ipPort) {
        this.ipPort = ipPort;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
