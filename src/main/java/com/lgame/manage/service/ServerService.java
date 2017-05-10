/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lgame.manage.service;

import com.lgame.manage.cache.ServerConnection;
import com.module.db.GameNotice;
import com.module.db.GameZone;
import com.module.db.Version;

import java.util.List;

/**
 *
 * @author leroy_boy
 */
public interface ServerService {
    public List<ServerConnection> getGateServers();

    public Version getVersion(int srcId, int gameId);

    public List<GameNotice> getGameStopingNotice(Version v);

    public List<GameNotice> getGameRuningNotice(Version v);

    public GameZone getGameZone(int quzoneId);
    public List<GameZone> getGameZones(int game_versionId);

}
