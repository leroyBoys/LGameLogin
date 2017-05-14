package com.lgame.model.net;

import com.lsocket.manager.CMDManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/13.
 */
public enum CmdEnum {
    FirstConnect(CMDManager.getCmd_M(0,2),NetFirstConnect.class),
    CreateRoom(CMDManager.getCmd_M(10,1),NetCreateRoom.class);

    private final int cmd;
    private final Class cls;
    CmdEnum(int cmd,Class cls){
        this.cmd = cmd;
        this.cls = cls;
    }

    public static final Map<Integer,CmdEnum> datas = new HashMap<>();
    static {
        for(CmdEnum cmdEnum:values()){
            datas.put(cmdEnum.getCmd(),cmdEnum);
        }
    }


    public int getCmd() {
        return cmd;
    }

    public Class getCls() {
        return cls;
    }
}
