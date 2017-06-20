package com.lgame.manage.service;

import com.module.db.UserDev;
import com.module.db.UserFrom;
import com.module.db.UserInfo;
import com.module.net.DB;

/**
 * Created by Administrator on 2017/5/7.
 */
public interface UserService {
    public UserInfo getUserInfo(String name);

    @Deprecated
    public boolean updateUserInfoStatus(int id, String userName, String userPwd, String inviteCode);

    public UserInfo insertUserInfo(UserInfo info);

    public UserDev insertDev(String info, String plat, String mac, String udid, int os_id);

    public void updateUserInfoLastDev(int uid, int devId);

    public void updateUserToken(int id, String key, String ip);

    public DB.UK getUk(int uid);

    public UserInfo getUserInfo(int id);

    public UserInfo getUserInfoByUserFormId(int id);

    public boolean updatepwd(int id, String newPwd);

    public int getUserFrom(String userSrc);

    public int insertFrom(UserFrom from);

    public void addDefalutGameRoleDetail(int uid,String userName, String sex, String headimgurl);
}
