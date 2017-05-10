/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lgame.manage.job;

import com.lgame.manage.cache.ServerManager;
import com.lgame.util.time.DateTimeTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 系统固定时间执行job
 *
 * @author lxh
 */
@Component()
@Scope("singleton")
public class SystemJob {

    private static final Logger logger = LoggerFactory.getLogger(SystemJob.class);

    @Scheduled(cron = "0 */5 *  * * ? ")//测试每5分钟执行一次
    public void fiveMinuteJob() {
        //  logger.warn("05定时检测系统开始扫描----" + DateUtil.getDateTime(new Date()));

        //logger.warn("05定时检测系统扫描完成" + DateUtil.getDateTime(new Date()));
    }

    @Scheduled(cron = "*/20 * *  * * ? ")//测试每20秒钟执行一次
    public void fiveSecondJob() {
        System.out.println("============>执行一次:"+ DateTimeTool.getDateTime(new Date()));
        ServerManager.getIntance().run();
    }

    @Scheduled(cron = "0 */1 *  * * ? ")//测试每1分钟执行一次
    public void oneMinuteJob() {

    }

    @Scheduled(cron = "59 59 23  * * ? ")//每天晚上23带你59分59秒
    public void midnight() {
    }
}
