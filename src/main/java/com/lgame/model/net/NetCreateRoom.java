package com.lgame.model.net;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import java.util.List;

/**
 * Created by Administrator on 2017/5/13.
 */
public class NetCreateRoom {
    @Protobuf(fieldType = FieldType.INT32, order = 1, required = true)
    private int roomId;
    @Protobuf(fieldType = FieldType.INT32, order = 2, required = true)
    private int gameId;
    @Protobuf(fieldType = FieldType.INT32, order = 3, required = false)
    private List<Integer> type;

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public List<Integer> getType() {
        return type;
    }

    public void setType(List<Integer> type) {
        this.type = type;
    }
}
