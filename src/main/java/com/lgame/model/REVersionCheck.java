package com.lgame.model;

import java.util.List;

/**
 * Created by Administrator on 2017/5/4.
 */
public class REVersionCheck {
    private String version;
    private List<String> noticeVersions;
    private int gameId;
    private int srcId;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }


    public List<String> getNoticeVersions() {
        return noticeVersions;
    }

    public void setNoticeVersions(List<String> noticeVersions) {
        this.noticeVersions = noticeVersions;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getSrcId() {
        return srcId;
    }

    public void setSrcId(int srcId) {
        this.srcId = srcId;
    }
}
