package com.lgame.manage.cache;

import com.lgame.manage.dao.UserRedis;
import com.lgame.manage.service.ServerService;
import com.module.net.DB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
 * Created by Administrator on 2017/4/17.
 */
public class GateServerCache {
    private static final Logger logger = LoggerFactory.getLogger(GateServerCache.class);
    @Autowired
    private ServerService serverService;
    @Autowired
    private UserRedis userRedis;

    @Value("${redis.host}")
    private String redisIp;
    @Value("${url}")
    private String mysqlip;

    public void start() {
        System.out.println("=====>"+redisIp);
        System.out.println("=====>"+mysqlip);
        initServerManager();
    }

    private void initServerManager(){
        List<ServerConnection> servers = serverService.getGateServers();
        ServerManager.getIntance().init(servers);

        DB.UK.Builder uk = DB.UK.newBuilder();
        uk.setIpPort("192.168.1.1:80");
        uk.setUid(12);
        uk.setKey("sds");
        userRedis.save(uk.build());
    }

    public void destroy() {
    }
}
