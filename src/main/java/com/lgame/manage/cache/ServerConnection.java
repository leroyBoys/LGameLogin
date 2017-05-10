package com.lgame.manage.cache;

import com.lgame.codec.RequestDecoderLocal;
import com.lgame.codec.ResponseEncoderLocal;
import com.logger.log.SystemLogger;
import com.lsocket.core.ClientServer;
import com.lsocket.listen.HandlerListen;
import com.lsocket.util.DefaultSocketPackage;
import com.module.GameServer;
import org.apache.mina.core.session.IoSession;

/**
 * Created by leroy:656515489@qq.com
 * 2017/4/6.
 */
public class ServerConnection extends GameServer implements Runnable {
    public static final ServerConnection instance = new ServerConnection();
    private volatile ServerStatus runStatus = ServerStatus.closed;
    private int heartPerTime = 1000;//心跳间隔毫秒
    private final static int timeOutTime = 5*60*1000;//超时时间

    private ServerMonitor serverMonitor = new ServerMonitor();
    private ClientServer clientServer;
    private volatile boolean isRun = false;
    public ServerConnection(){}

    public void initClientServer(){
        if(clientServer != null){
            return;
        }
        clientServer = new ClientServer(this.getIp(),this.getPort(),2000,new ResponseEncoderLocal(),new RequestDecoderLocal(),serverMonitor);
    }

    public void check(long curTime){
        this.initClientServer();
        if(isRun){
            return;
        }
        isRun = true;
        checkConnected();
        heart(curTime);
    }

    private void checkConnected(){
        if(runStatus != ServerStatus.closed){
            return;
        }

        reConnect();
    }

    private void reConnect(){
        new Thread(this).run();
    }

    public ServerStatus getRunStatus() {
        return runStatus;
    }

    private void heart(long curTime){
        long dif = curTime - serverMonitor.lastHeartTime;
        if(runStatus == ServerStatus.closed || dif < heartPerTime){
            return;
        }
        try {
            if(dif > timeOutTime){
                reConnect();
                return;
            }



            send(DefaultSocketPackage.transformHeartMsg());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            isRun = false;
        }
    }

    @Override
    protected ServerConnection createNew() {
        return new ServerConnection();
    }

    public void send(Object obj){
        serverMonitor.session.write(obj);
    }

    private int errorNum = 0;

    @Override
    public void run() {
        try {
            if(clientServer.getSession() != null){
                clientServer.getSession().closeNow();
            }

            clientServer.start();
            if(clientServer.getSession() != null){
                serverMonitor.session = clientServer.getSession();
                runStatus = ServerStatus.notFull;
                SystemLogger.info(this.getClass(),"ip:"+this.getIp()+" port:"+this.getPort()+" connected suc!");
            }else {
                errorNum++;
                if(errorNum/20 == 1){
                    SystemLogger.info(this.getClass(),"ip:"+this.getIp()+" port:"+this.getPort()+" connected fail!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            isRun = false;
        }
    }

    class ServerMonitor implements HandlerListen {
        protected long lastHeartTime = 0;
        protected int connections;
        protected IoSession session = new ServerSession();

        protected void init(){
        }

        protected void reset(){
            connections = 0;
            lastHeartTime = 0;
            session = new ServerSession();
        }

        @Override
        public void sendSuc() {
        }

        @Override
        public void receiveMsg() {
            lastHeartTime = System.currentTimeMillis();
        }

    }

    public enum ServerStatus{
        closed,notFull,full,waitRestart
    }
}
