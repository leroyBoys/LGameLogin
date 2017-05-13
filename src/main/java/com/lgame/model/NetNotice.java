package com.lgame.model;

/**
 * Created by Administrator on 2017/5/7.
 */
public class NetNotice {
    private int id;
    private int type;
    private String titleName;
    private String titleDesc;
    private String startTime;
    private String endTime;

    public NetNotice() {
    }

    public NetNotice(int type, String titleName, String titleDesc, String startTime, String endTime) {
        this.type = type;
        this.titleName = titleName;
        this.titleDesc = titleDesc;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getTitleDesc() {
        return titleDesc;
    }

    public void setTitleDesc(String titleDesc) {
        this.titleDesc = titleDesc;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
