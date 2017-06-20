package com.lgame.manage.dao.impl;

import com.lgame.base.dao.BaseDao;
import com.lgame.manage.dao.UserDao;
import com.lgame.util.comm.StringTool;
import com.module.db.UserDev;
import com.module.db.UserFrom;
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
public class UserDaoImpl extends BaseDao implements UserDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserInfo getUserInfo(String name) {
        return getUserInfoFromDb("SELECT * FROM user_info WHERE user_name ='"+name+"'");
    }

    @Override
    public boolean updateUserInfoStatus(int uid, String userName, String pwd, String invite_code) {
        return this.executeUpdate(jdbcTemplate,"CALL SET_USERINFO_AGAIN (?,?,?,?)",  uid, userName, pwd, invite_code);
    }

    @Override
    public int findDevId(String device_mc, String udid) {
        try {
            Object obj = this.executesOneResult(jdbcTemplate,"SELECT id FROM user_device WHERE device_mac = ?  AND udid = ? ",device_mc,udid);
            if(obj == null){
                return 0;
            }

            return (int) obj;
        } catch (Exception ex) {
        } finally {
        }
        return 0;
    }

    @Override
    public UserDev insertDev(UserDev dev) {
        try {
           int id =  this.insert(jdbcTemplate,"INSERT INTO user_device(device_info,device_name,os_id,device_mac,udid,create_date)VALUES(?,?,?,?,?,?)",
                    dev.getDeviceInfo(),
                    dev.getDeviceName(),
                    dev.getOsId(),
                    dev.getDeviceMac(),
                    dev.getUdid(),
                    dev.getCreateDate());
            dev.setId(id);
            return dev;
        } catch (Exception ex) {
        } finally {
        }
        return null;
    }

    @Override
    public UserInfo insertUserInfo(UserInfo info) {
        try {
            int id = this.insert(jdbcTemplate,"INSERT INTO user_info(device_id,user_from_type,user_from_id,user_name,user_pwd,role,invite_code,user_status,status_endtime,create_date)"
                    + "			VALUES(?,?,?,?,?,?,?,?,?,?)", info.getDeviceId(),
                    info.getUserFromType(),
                    info.getUserFromId(),
                    info.getUserName(),
                    info.getUserPwd(),
                    info.getRole(),
                    info.getInviteCode(),
                    info.getUserStatus(),
                    info.getStatusEndTime(),
                    info.getCreateDate());
            info.setId(id);
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
            ex.printStackTrace();
        } finally {
        }
        return null;
    }

    @Override
    public void updateUserInfoLastDev(int uid, int devId) {
        this.executeUpdate(jdbcTemplate,"UPDATE user_info SET device_id = ?,is_online=0  WHERE id = ?",devId,uid);
    }

    @Override
    public UserInfo getUserInfoByUserFormId(int formId) {
        return getUserInfoFromDb("SELECT * FROM user_info WHERE user_from_id = '"+formId+"'");
    }

    @Override
    public boolean updatepwd(int id, String newPwd) {
        return this.executeUpdate(jdbcTemplate,"UPDATE user_info SET user_pwd = ? WHERE id = ?",newPwd,id);
    }

    @Override
    public int getUserFrom(String userSrc) {
        try {
            Object obj = this.executesOneResult(jdbcTemplate,"SELECT id FROM `user_from` WHERE user_src = ? ",userSrc);
            if(obj == null){
                return 0;
            }

            return (int) obj;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
        }
        return 0;
    }

    @Override
    public int insertFrom(UserFrom from) {
        try {
            int id = this.insert(jdbcTemplate,"INSERT INTO user_from(user_src,serial_num,info,create_date)VALUES(?,?,?,?)",
                    from.getUserSrc(), from.getSerialNum(), from.getInfo(), from.getCreateDate());
            return id;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
        }
        return 0;
    }
}
