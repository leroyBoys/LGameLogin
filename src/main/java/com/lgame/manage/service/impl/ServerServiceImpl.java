/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lgame.manage.service.impl;

import com.lgame.manage.cache.ServerConnection;
import com.lgame.manage.cache.ServerManager;
import com.lgame.manage.dao.ServerDao;
import com.lgame.manage.service.ServerService;
import com.lgame.util.time.DateTimeTool;
import com.module.db.GameNotice;
import com.module.db.GameZone;
import com.module.db.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 *
 * @author leroy_boy
 */
@Service
public class ServerServiceImpl implements ServerService {
    @Autowired
    private ServerDao serverDao;

    @Override
    public List<ServerConnection> getGateServers() {
        return serverDao.getGateServers();
    }

    @Override
    public Version getVersion(int srcId, int gameId) {
        return serverDao.getVersion(srcId, gameId);
    }

    @Override
    public List<GameNotice> getGameStopingNotice(Version v) {
        return serverDao.getGameStopingNotice(v.getF_ids());
    }

    @Override
    public List<GameNotice> getGameRuningNotice(Version v) {
        return serverDao.getGameRuningNotice(v.getF_ids());
    }

    @Override
    public GameZone getGameZone(int quzoneId) {
        return serverDao.getGameZone(quzoneId);
    }

    @Override
    public List<GameZone> getGameZones(int game_versionId) {
        return serverDao.getGameZones(game_versionId);
    }
}
