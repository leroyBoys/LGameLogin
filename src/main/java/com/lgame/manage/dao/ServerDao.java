package com.lgame.manage.dao;

import com.lgame.manage.cache.ServerConnection;
import com.module.db.GameNotice;
import com.module.db.GameZone;
import com.module.db.Version;

import java.util.List;

/**
 * Created by Administrator on 2017/5/7.
 */
public interface ServerDao {

    public List<ServerConnection> getGateServers();
    public Version getVersion(int srcId, int gameId);

    public List<GameNotice> getGameStopingNotice(String ids);

    public List<GameNotice> getGameRuningNotice(String ids);

    public GameZone getGameZone(int quzoneId);
    public List<GameZone> getGameZones(int game_versionId);

}
