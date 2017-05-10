package com.lgame.model;

/**
 * Created by Administrator on 2017/5/4.
 */
public class NetZone {
    private String ip;
    private int port;
    private int udpport;
    private int status;
    private int id;
    private String name;

    public NetZone(String ip, int port, int udpport, int status, int id, String name) {
        this.ip = ip;
        this.port = port;
        this.udpport = udpport;
        this.status = status;
        this.id = id;
        this.name = name;
    }


    public NetZone() {
    }

    public String getIp() {
        return ip;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getAll() {
        return ip + ":" + port;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUdpport() {
        return udpport;
    }

    public void setUdpport(int udpport) {
        this.udpport = udpport;
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
