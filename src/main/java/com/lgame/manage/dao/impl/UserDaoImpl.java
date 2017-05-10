package com.lgame.manage.dao.impl;

import com.lgame.manage.dao.UserDao;
import com.lgame.util.comm.StringTool;
import com.module.db.UserDev;
import com.module.db.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Administrator on 2017/5/7.
 */
@Repository
public class UserDaoImpl implements UserDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserInfo getUserInfo(String name) {
        return getUserInfoFromDb("SELECT * FROM user_info WHERE user_name ='"+name+"'");
    }

    @Override
    public boolean updateUserInfoStatus(int uid, String userName, String pwd, String invite_code) {
        jdbcTemplate.execute(StringTool.Format("CALL SET_USERINFO_AGAIN({0},{1},{2},{3})", new Object[]{
                uid, userName, pwd, invite_code
        }));
        return true;
    }

    @Override
    public int findDevId(String device_mc, String udid) {
        try {
            return jdbcTemplate.execute("SELECT id FROM user_device WHERE device_mac = "+device_mc+"  AND udid = "+udid, new PreparedStatementCallback<Integer>() {
                @Override
                public Integer doInPreparedStatement(PreparedStatement cs) throws SQLException, DataAccessException {
                    ResultSet rs = cs.executeQuery();
                    if(rs.next()){
                        try {
                            return rs.getInt("id");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    return null;
                }
            });
        } catch (Exception ex) {
        } finally {
        }
        return 0;
    }

    @Override
    public UserDev insertDev(UserDev dev) {
        try {
            jdbcTemplate.execute(StringTool.Format("INSERT INTO user_device(device_info,device_name,os_id,device_mac,udid,create_date)VALUES({0},{1},{2},{3},{4},{5})", new Object[]{
                    dev.getDeviceInfo(),
                    dev.getDeviceName(),
                    dev.getOsId(),
                    dev.getDeviceMac(),
                    dev.getUdid(),
                    dev.getCreateDate()
            }));
            return dev;
        } catch (Exception ex) {
        } finally {
        }
        return null;
    }

    @Override
    public UserInfo insertUserInfo(UserInfo info) {
        try {
            jdbcTemplate.execute(StringTool.Format("INSERT INTO user_info(device_id,user_from_type,user_from_id,user_name,user_pwd,role,invite_code,user_status,status_endtime,create_date)"
                    + "			VALUES({0},{1},{2},{3},{4},{5},{6},{7},{8},{9})", new Object[]{
                    info.getDeviceId(),
                    info.getUserFromType(),
                    info.getUserFromId(),
                    info.getUserName(),
                    info.getUserPwd(),
                    info.getRole(),
                    info.getInviteCode(),
                    info.getUserStatus(),
                    info.getStatusEndTime(),
                    info.getCreateDate()
            }));
            return info;
        } catch (Exception ex) {
        } finally {
        }
        return null;
    }

    @Override
    public UserInfo getUserInfo(int id) {
        return getUserInfoFromDb("SELECT * FROM user_info WHERE id="+id);
    }

    private UserInfo getUserInfoFromDb(String sql) {
        try {
            return jdbcTemplate.execute(sql, new PreparedStatementCallback<UserInfo>() {
                @Override
                public UserInfo doInPreparedStatement(PreparedStatement cs) throws SQLException, DataAccessException {
                    ResultSet rs = cs.executeQuery();
                    if(rs.next()){
                        try {
                            return UserInfo.instance.create(rs);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    return null;
                }
            });
        } catch (Exception ex) {
        } finally {
        }
        return null;
    }

    @Override
    public void updateUserInfoLastDev(int uid, int devId) {

    }

    @Override
    public UserInfo getUserInfoByUserFormId(int formId) {
        return getUserInfoFromDb("SELECT * FROM user_info WHERE user_from_id = '"+formId+"'");
    }
}
