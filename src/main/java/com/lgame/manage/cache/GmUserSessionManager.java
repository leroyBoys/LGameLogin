package com.lgame.manage.cache;

import com.lgame.codec.BdResponse;
import com.lgame.codec.RequestDecoderLocal;
import com.lgame.codec.ResponseEncoderLocal;
import com.lgame.model.net.CmdMsg;
import com.lsocket.core.ClientServer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/14.
 */
public class GmUserSessionManager {
    private static GmUserSessionManager ourInstance = new GmUserSessionManager();

    public static GmUserSessionManager getInstance() {
        return ourInstance;
    }

    private GmUserSessionManager() {
    }
    public static final Map<Integer,ClientServer> clenets = new HashMap<>();
    public Map<Integer,Object> msgReceIves = new HashMap<>();

    public void connect(int uid,String key,ServerConnection serverConnection){

        ServerConnection.ServerMonitor serverMonitor = new ServerConnection.ServerMonitor();
        ClientServer clientServer = new ClientServer(serverConnection.getIp(),serverConnection.getPort(),2000,key,new ResponseEncoderLocal(),new RequestDecoderLocal(),serverMonitor);
        clientServer.start();
        serverMonitor.setUid(uid);
        clenets.put(uid,clientServer);
    }

    public void sendMsg(int uid, BdResponse obj) {
       clenets.get(uid).getSession().write(obj);
    }

    public void receive(int uid, Object object) {
        msgReceIves.put(uid,object);

        ClientServer clientServer = clenets.get(uid);
        if(clientServer == null){
            System.out.println("============>uid"+uid+" msg receiver but not fin clent");
            return;
        }
        synchronized (clientServer){
            clientServer.notify();
        }
    }
}
