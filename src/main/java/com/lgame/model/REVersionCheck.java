package com.lgame.model;

import java.util.List;

/**
 * Created by Administrator on 2017/5/4.
 */
public class REVersionCheck {
    private String version;
    private List<Integer> noticeVersions;
    private int gameId;
    private int srcId;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }


    public List<Integer> getNoticeVersions() {
        return noticeVersions;
    }

    public void setNoticeVersions(List<Integer> noticeVersions) {
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
