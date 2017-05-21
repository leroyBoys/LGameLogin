package com.lgame.model.net;
import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;


/** 
 * 
 * auto class not modify 
 * 
 */
public class RPEnterRoom {
	@Protobuf(fieldType = FieldType.INT32, order =  1, required = false)
	private int roomId;
	@Protobuf(fieldType = FieldType.STRING, order =  2, required = false)
	private String key;

	public void setroomId(int roomId){
		this.roomId=roomId;
	}
	public int getroomId(){
		return roomId;
	}
	public void setkey(String key){
		this.key=key;
	}
	public String getkey(){
		return key;
	}
}
