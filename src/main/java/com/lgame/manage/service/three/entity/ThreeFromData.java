package com.lgame.manage.service.three.entity;

import java.util.Map;

/**
 * Created by leroy:656515489@qq.com
 * 2017/6/20.
 */
public class ThreeFromData {
    private String thirdKey;
    private String ukey;//本app中用户的唯一识别信息
    private String refresh_token;
    private Map<String,String> paramters;//其他参数

    public ThreeFromData(String thirdKey, String ukey) {
        this.thirdKey = thirdKey;
        this.ukey = ukey;
    }

    public String getThirdKey() {
        return thirdKey;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public Map<String, String> getParamters() {
        return paramters;
    }

    public void setParamters(Map<String, String> paramters) {
        this.paramters = paramters;
    }

    public void setThirdKey(String thirdKey) {
        this.thirdKey = thirdKey;
    }

    public String getUkey() {
        return ukey;
    }

    public void setUkey(String ukey) {
        this.ukey = ukey;
    }
}
