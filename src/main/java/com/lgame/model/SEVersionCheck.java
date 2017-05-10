package com.lgame.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/4.
 */
public class SEVersionCheck {
    private String version;
    private String goUrl;
    private int quzoneStatus;
    private List<NetNotice> noticeVersions;
    private String downv;
    private String downr;
    private List<NetZone> zones;


    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getGoUrl() {
        return goUrl;
    }

    public void setGoUrl(String goUrl) {
        this.goUrl = goUrl;
    }

    public int getQuzoneStatus() {
        return quzoneStatus;
    }

    public void setQuzoneStatus(int quzoneStatus) {
        this.quzoneStatus = quzoneStatus;
    }

    public List<NetNotice> getNoticeVersions() {
        return noticeVersions;
    }

    public List<NetZone> getZones() {
        return zones;
    }

    public void setZones(List<NetZone> zones) {
        this.zones = zones;
    }

    public void addZone(NetZone zo){
        if(zones == null){
            zones = new ArrayList<>();
        }
        zones.add(zo);
    }
    public void setNoticeVersions(List<NetNotice> noticeVersions) {
        this.noticeVersions = noticeVersions;
    }


    public String getDownv() {
        return downv;
    }

    public void setDownv(String downv) {
        this.downv = downv;
    }

    public String getDownr() {
        return downr;
    }

    public void setDownr(String downr) {
        this.downr = downr;
    }

}
