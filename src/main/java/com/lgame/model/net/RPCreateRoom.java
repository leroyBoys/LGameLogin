package com.lgame.model.net;
import java.util.*;
import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;


/** 
 * 
 * auto class not modify 
 * 
 */
public class RPCreateRoom {
	@Protobuf(fieldType = FieldType.INT32, order =  1, required = false)
	private int roomId;
	@Protobuf(fieldType = FieldType.INT32, order =  2, required = false)
	private int gameId;
	@Protobuf(fieldType = FieldType.INT32, order =  3, required = false)
	private List<Integer> type;

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
	public void settype(List<Integer> type){
		this.type=type;
	}
	public List<Integer> gettype(){
		return type;
	}
}
