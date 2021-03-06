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

    public boolean connect(int uid,String key,ServerConnection serverConnection){

        ServerConnection.ServerMonitor serverMonitor = new ServerConnection.ServerMonitor();
        ClientServer clientServer = new ClientServer(serverConnection.getIp(),serverConnection.getPort(),2000,key,new ResponseEncoderLocal(),new RequestDecoderLocal(),serverMonitor);
        clientServer.start();
        if(clientServer.getSession() == null){
            return false;
        }

        serverMonitor.setUid(uid);
        clenets.put(uid,clientServer);

        System.out.println("=============>connect suc:uid:"+uid);
        return true;
    }

    public void sendMsg(int uid, BdResponse obj) {
       clenets.get(uid).getSession().write(obj);
    }

    public void receive(int uid, Object object) {
        msgReceIves.put(uid,object);
        System.out.println("============>uid"+uid+" msg receiver msg");
        ClientServer clientServer = clenets.get(uid);
        if(clientServer == null){
            System.out.println("============>uid"+uid+" msg receiver but not find clent");
            return;
        }
        synchronized (clientServer){
            clientServer.notify();
        }
    }

    public Object getMsg(int uid) {
        System.out.println("====remover before:uid:"+uid+" obj:"+msgReceIves.get(uid));
        Object obj = msgReceIves.remove(uid);
        System.out.println("====remover after:uid:"+uid+" obj:"+msgReceIves.get(uid));
        return  obj;
    }
}
