/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lgame.model;

/**
 *
 * @author lxh
 */
public class REregister {
    private String name;
    private String pwd;
    private NetDevice dev;
    private String code;

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

    public NetDevice getDev() {
        return dev;
    }

    public void setDev(NetDevice dev) {
        this.dev = dev;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
