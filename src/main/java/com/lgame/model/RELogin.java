package com.lgame.model;

/**
 * Created by Administrator on 2017/5/4.
 */
public class RELogin {
    private String userName;
    private String pwd;
    private NetDevice dev;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public NetDevice getDev() {
        return dev;
    }

    public void setDev(NetDevice dev) {
        this.dev = dev;
    }

}
