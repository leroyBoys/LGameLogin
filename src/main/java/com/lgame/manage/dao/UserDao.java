package com.lgame.manage.dao;

import com.module.db.UserDev;
import com.module.db.UserInfo;

/**
 * Created by Administrator on 2017/5/7.
 */
public interface UserDao {
    public UserInfo getUserInfo(String name);

    public UserInfo getUserInfo(String name, String pwd);

    public boolean updateUserInfoStatus(int uid, String userName, String pwd, String invite_code);

    public int findDevId(String device_mc, String udid);

    public UserDev insertDev(UserDev dev);

    public UserInfo insertUserInfo(UserInfo info);

    public UserInfo getUserInfo(int id);

    public void updateUserInfoLastDev(int uid, int devId);

    public UserInfo getUserInfoByUserFormId(int formId);

    public boolean updatepwd(int id, String newPwd);
}
