package com.lgame.model;

/**
 * Created by Administrator on 2017/5/4.
 */
public class SELogin {
    private int uid;
    private String key;
    public SELogin() {
    }

    public SELogin(int uid, String key) {
        this.uid = uid;
        this.key = key;
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
}
