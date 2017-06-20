package com.lgame.manage.service.three;

import com.lgame.manage.service.three.entity.ThreeFromData;
import com.lgame.manage.service.three.entity.ThreeFromUserData;
import com.lgame.util.http.HttpTool;
import com.lgame.util.json.JsonUtil;
import com.logger.log.SystemLogger;
import com.module.FromType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by leroy:656515489@qq.com
 * 2017/6/20.
 */
public class WeiXinFrom implements ThreeFrom {
    private final String appId ="";
    private final String secret ="";

    @Override
    public FromType getKey() {
        return FromType.wx;
    }

    @Override
    public ThreeFromData checkUser(String code) {
        Map<String,String> paramMap = new HashMap<String,String>();
        paramMap.put("appid",appId);

        paramMap.put("grant_type", "authorization_code");
        paramMap.put("secret", secret);
        paramMap.put("code", code);
        String json = HttpTool.httpClient("https://api.weixin.qq.com/sns/oauth2/access_token", paramMap);

        HashMap<String, String> res = (HashMap<String, String>) JsonUtil.getBeanFromJson(json,HashMap.class);
        SystemLogger.info(this.getClass(),"ask weixin access_token, \t request:" + JsonUtil.getJsonFromBean(paramMap) + "\t,response:"+json);
        if (res.get("errcode") == null) {

            ThreeFromData threeFromData = new ThreeFromData(res.get("access_token"),res.get("openid"));
            return threeFromData;
        }
        return null;
    }

    @Override
    public boolean checkUser(String thirdKey, String ukey) {
        Map<String,String> paramMap = new HashMap<String,String>();
        paramMap.put("access_token",thirdKey);
        paramMap.put("openid", ukey);
        String json = HttpTool.httpClient("https://api.weixin.qq.com/sns/auth", paramMap);

        HashMap<String, String> res = (HashMap<String, String>) JsonUtil.getBeanFromJson(json,HashMap.class);
        SystemLogger.info(this.getClass(),"checkUser weixin access_token, \t request:" + JsonUtil.getJsonFromBean(paramMap) + "\t,response:"+json);
        return res.get("errcode") == null || res.get("errcode").equals("0");
    }

    @Override
    public ThreeFromUserData getThreeFromUserData(String thirdKey, String ukey) {
        Map<String,String> paramMap = new HashMap<String,String>();
        paramMap.put("access_token", thirdKey);
        paramMap.put("openid", ukey);
        String json = HttpTool.httpClient("https://api.weixin.qq.com/sns/userinfo", paramMap);

        HashMap<String, String> res = (HashMap<String, String>) JsonUtil.getBeanFromJson(json,HashMap.class);
        SystemLogger.info(this.getClass(),"ask weixin access_token, \t request:" + JsonUtil.getJsonFromBean(paramMap) + "\t,response:"+json);
        if (res.get("errcode") == null) {

            ThreeFromUserData threeFromData = new ThreeFromUserData();

            threeFromData.setSex(res.get("sex"));
            threeFromData.setNickname(res.get("nickname"));
            threeFromData.setProvince(res.get("province"));
            threeFromData.setCity(res.get("city"));
            threeFromData.setCountry(res.get("country"));
            threeFromData.setHeadimgurl(res.get("headimgurl"));
            threeFromData.setUkey(res.get("openid"));
            threeFromData.setUnionid(res.get("unionid"));
            return threeFromData;
        }
        return null;
    }

}
