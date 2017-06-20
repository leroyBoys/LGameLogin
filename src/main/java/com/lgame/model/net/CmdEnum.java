package com.lgame.model.net;

import com.lsocket.manager.CMDManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/13.
 */
public enum CmdEnum {
    FirstConnect(CMDManager.getCmd_M(0,2),NetFirstConnect.class, RQConnect.class),
    CreateRoom(CMDManager.getCmd_M(10,1),RPCreateRoom.class,RQCreateRoom.class),
    JoinRoom(CMDManager.getCmd_M(10,2),RPEnterRoom.class,RQEnterRoom.class),
    ExitRoom(CMDManager.getCmd_M(10,3),null,RQExit.class),
    Ready(CMDManager.getCmd_M(10,5),null,NetResponse.class),
    ;

    private final int cmd;
    private final Class cls;
    private final Class recCls;
    CmdEnum(int cmd,Class cls,Class recCls){
        this.cmd = cmd;
        this.cls = cls;
        this.recCls = recCls;
    }

    public static final Map<Integer,CmdEnum> datas = new HashMap<>();
    static {
        for(CmdEnum cmdEnum:values()){
            datas.put(cmdEnum.getCmd(),cmdEnum);
        }
    }

    public Class getRecCls() {
        return recCls;
    }

    public int getCmd() {
        return cmd;
    }

    public Class getCls() {
        return cls;
    }
}
