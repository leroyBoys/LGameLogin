package com.lgame.model.net;
import java.util.*;
import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;


/** 
 * 
 * auto class not modify 
 * 
 */
public class RQCreateRoom {
	@Protobuf(fieldType = FieldType.INT32, order =  1, required = false)
	private int roomId;
	@Protobuf(fieldType = FieldType.INT32, order =  2, required = false)
	private int gameId;
	@Protobuf(fieldType = FieldType.OBJECT, order =  3, required = false)
	private List<NetUserData> users;
	@Protobuf(fieldType = FieldType.INT32, order =  4, required = false)
	private List<Integer> type;
	@Protobuf(fieldType = FieldType.INT32, order =  5, required = false)
	private int curRount;
	@Protobuf(fieldType = FieldType.INT32, order =  6, required = false)
	private int gameStatus;
	@Protobuf(fieldType = FieldType.INT32, order =  7, required = false)
	private int ownerId;
	@Protobuf(fieldType = FieldType.OBJECT, order =  8, required = false)
	private NetExtraData extra;

	public void setroomId(int roomId){
		this.roomId=roomId;
	}
	public int getroomId(){
		return roomId;
	}
	public void setgameId(int gameId){
		this.gameId=gameId;
	}
	public int getgameId(){
		return gameId;
	}
	public void setusers(List<NetUserData> users){
		this.users=users;
	}
	public List<NetUserData> getusers(){
		return users;
	}
	public void settype(List<Integer> type){
		this.type=type;
	}
	public List<Integer> gettype(){
		return type;
	}
	public void setcurRount(int curRount){
		this.curRount=curRount;
	}
	public int getcurRount(){
		return curRount;
	}
	public void setgameStatus(int gameStatus){
		this.gameStatus=gameStatus;
	}
	public int getgameStatus(){
		return gameStatus;
	}
	public void setownerId(int ownerId){
		this.ownerId=ownerId;
	}
	public int getownerId(){
		return ownerId;
	}
	public void setextra(NetExtraData extra){
		this.extra=extra;
	}
	public NetExtraData getextra(){
		return extra;
	}
}
