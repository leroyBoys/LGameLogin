package com.lgame.manage.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by Administrator on 2017/4/17.
 */
public class GateServerCache {
    private static final Logger logger = LoggerFactory.getLogger(GateServerCache.class);

    @Value("${redis.host}")
    private String redisIp;

    @Value("${mysql.business.database.url}")
    private String mysqlip;

    public void start() {

    }

    public void destroy() {
    }
}
