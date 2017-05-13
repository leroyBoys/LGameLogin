/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lgame.manage.dao.impl;

import com.lgame.manage.cache.ServerConnection;
import com.lgame.manage.dao.ServerDao;
import com.lgame.staticdata.dao.StaticDataDaoImpl;
import com.module.db.GameNotice;
import com.module.db.GameZone;
import com.module.db.Version;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author leroy_boy
 */
@Repository
public class ServerDaoImpl implements ServerDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static Logger logger = Logger.getLogger(ServerDaoImpl.class);

    @Override
    public List<ServerConnection> getGateServers() {
        try {
            return jdbcTemplate.execute("SELECT * FROM game_server WHERE server_type = 'gate'", new PreparedStatementCallback<List<ServerConnection>>() {
                @Override
                public List<ServerConnection> doInPreparedStatement(PreparedStatement cs) throws SQLException, DataAccessException {
                    ResultSet rs = cs.executeQuery();
                    List<ServerConnection> serverConnections = new LinkedList<>();
                    while(rs.next()){
                        try {

                            serverConnections.add((ServerConnection) ServerConnection.instance.create(rs));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    return serverConnections;
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
        }
        return null;
    }

    @Override
    public Version getVersion(int srcId, int gameId) {
        try {
            return jdbcTemplate.execute("SELECT * FROM game_version WHERE src_id = "+srcId+" AND game_id = "+gameId, new PreparedStatementCallback<Version>() {
                @Override
                public Version doInPreparedStatement(PreparedStatement cs) throws SQLException, DataAccessException {
                    ResultSet rs = cs.executeQuery();
                    if(rs.next()){
                        try {
                            return Version.intance.create(rs);
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
    public List<GameNotice> getGameStopingNotice(String ids) {
        return getGameRuningNotice("SELECT * FROM game_notices WHERE TYPE = 1",ids);
    }

    @Override
    public List<GameNotice> getGameRuningNotice(String ids) {
        return getGameRuningNotice("SELECT * FROM game_notices WHERE TYPE = 2",ids);
    }

    private List<GameNotice> getGameRuningNotice(String sql,String ids) {
        try {
            return jdbcTemplate.execute(sql, new PreparedStatementCallback<List<GameNotice>>() {
                @Override
                public List<GameNotice> doInPreparedStatement(PreparedStatement cs) throws SQLException, DataAccessException {
                    ResultSet rs = cs.executeQuery();
                    List<GameNotice> ret = new LinkedList<>();
                    while (rs.next()){
                        try {
                            ret.add(GameNotice.instance.create(rs));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    return ret;
                }
            });
        } catch (Exception ex) {
        } finally {
        }
        return null;
    }

    @Override
    public GameZone getGameZone(int quzoneId) {
        try {
            return jdbcTemplate.execute("SELECT * FROM game_server WHERE id ="+quzoneId, new PreparedStatementCallback<GameZone>() {
                @Override
                public GameZone doInPreparedStatement(PreparedStatement cs) throws SQLException, DataAccessException {
                    ResultSet rs = cs.executeQuery();
                    if(rs.next()){
                        try {
                            return GameZone.instance.create(rs);
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
    public List<GameZone> getGameZones(int game_versionId) {
        try {
            return jdbcTemplate.execute("SELECT * FROM game_server WHERE g_v_id ="+game_versionId, new PreparedStatementCallback<List<GameZone>>() {
                @Override
                public List<GameZone> doInPreparedStatement(PreparedStatement cs) throws SQLException, DataAccessException {
                    ResultSet rs = cs.executeQuery();
                    List<GameZone> ret = new LinkedList<>();
                    while (rs.next()){
                        try {
                            ret.add(GameZone.instance.create(rs));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    return ret;
                }
            });
        } catch (Exception ex) {
        } finally {
        }
        return null;
    }

}
