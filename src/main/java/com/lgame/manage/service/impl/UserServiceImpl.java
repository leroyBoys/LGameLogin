package com.lgame.manage.service.impl;

import com.lgame.manage.dao.UserDao;
import com.lgame.manage.dao.UserRedis;
import com.lgame.manage.service.UserService;
import com.module.db.UserDev;
import com.module.db.UserInfo;
import com.module.net.DB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Administrator on 2017/5/7.
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRedis userRdis;

    @Override
    public UserInfo getUserInfo(String name) {
        return userDao.getUserInfo(name);
    }

    @Override
    public boolean updateUserInfoStatus(int uid, String userName, String pwd, String invite_code) {
        return userDao.updateUserInfoStatus(uid, userName, pwd, invite_code);
    }

    @Override
    public UserInfo insertUserInfo(UserInfo info) {
        return userDao.insertUserInfo(info);
    }

    @Override
    public UserDev insertDev(String device_info, String device_name, String device_mac, String udid, int os_id) {
        int devId = findDevId(device_mac, udid);
        UserDev dev = new UserDev();
        dev.setCreateDate(new Date());
        dev.setDeviceInfo(device_info);
        dev.setDeviceMac(device_mac);
        dev.setDeviceName(device_name);
        dev.setOsId(os_id);
        dev.setUdid(udid);
        dev.setId(devId);
        if (devId > 0) {
            return dev;
        }
        return userDao.insertDev(dev);
    }

    @Override
    public void updateUserInfoLastDev(int uid, int devId) {
        userDao.updateUserInfoLastDev(uid,devId);
    }

    @Override
    public void updateUserToken(int id, String key, String ip) {
        DB.UK.Builder uk = DB.UK.newBuilder();
        uk.setUid(id);
        uk.setKey(key);
        uk.setIpPort(ip);
        userRdis.save(uk.build());
    }

    @Override
    public DB.UK getUk(int uid) {
        return userRdis.getUk(uid);
    }

    @Override
    public UserInfo getUserInfo(int id) {
        return userDao.getUserInfo(id);
    }

    public int findDevId(String device_mc, String udid) {
        return userDao.findDevId(device_mc, udid);
    }

    @Override
    public UserInfo getUserInfoByUserFormId(int formId) {
        return userDao.getUserInfoByUserFormId(formId);
    }
}
