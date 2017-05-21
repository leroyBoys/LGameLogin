package com.lgame.model.net;
import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;


/** 
 * 
 * auto class not modify 
 * 
 */
public class RQConnect {
	@Protobuf(fieldType = FieldType.INT32, order =  1, required = false)
	private int roomId;

	public void setroomId(int roomId){
		this.roomId=roomId;
	}
	public int getroomId(){
		return roomId;
	}
}
