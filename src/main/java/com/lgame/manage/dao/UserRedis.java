package com.lgame.manage.dao;

import com.module.net.DB;

/**
 * Created by Administrator on 2017/5/7.
 */
public interface UserRedis {
    public void save(DB.UK uk);

    public DB.UK getUk(int uid);

    public void addDefalutGameRoleDetail(int uid,String userName, String sex, String headimgurl);
}
