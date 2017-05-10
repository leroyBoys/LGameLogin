package com.lgame.model;

/**
 *
 * @author leroy_boy
 */
public class SELoginThird {

    private int uid;
    private String key;
    private String name;
    private String pwd;
    private String ipPort;

    public SELoginThird() {
    }

    public SELoginThird(int uid, String key, String name, String pwd) {
        this.uid = uid;
        this.key = key;
        this.name = name;
        this.pwd = pwd;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getIpPort() {
        return ipPort;
    }

    public void setIpPort(String ipPort) {
        this.ipPort = ipPort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

}
