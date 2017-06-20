package com.lgame.manage.dao.impl;

import com.google.protobuf.InvalidProtocolBufferException;
import com.lgame.base.dao.RedisClient;
import com.lgame.manage.dao.UserRedis;
import com.lgame.util.comm.FormatDataTool;
import com.lgame.util.json.JsonTool;
import com.lgame.util.json.JsonUtil;
import com.module.db.RoleInfo;
import com.module.net.DB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/7.
 */
@Repository
public class UserRedisImpl implements UserRedis {
    @Autowired
    private RedisClient redisClient;

    @Override
    public void save(DB.UK uk) {
        redisClient.set(FormatDataTool.intToByteArray(uk.getUid()),uk.toByteArray());
    }

    @Override
    public DB.UK getUk(int uid) {
        byte[] datas = redisClient.get(FormatDataTool.intToByteArray(uid));
        if(datas == null){
            return null;
        }
        try {
            return DB.UK.parseFrom(datas);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addDefalutGameRoleDetail(int uid,String userName, String sex, String headimgurl) {
        String key = "GameRole"+uid;
        RoleInfo roleInfo = new RoleInfo();
        roleInfo.setHeadImage(headimgurl);
        roleInfo.setUserAlise(userName);
        roleInfo.setUserSex(Integer.valueOf(sex));
        redisClient.set(key, JsonUtil.getJsonFromBean(roleInfo));
    }
}
