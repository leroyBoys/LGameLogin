package com.lgame.model.net;
import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;


/** 
 * 
 * auto class not modify 
 * 
 */
public class RQEnterRoom {
	@Protobuf(fieldType = FieldType.OBJECT, order =  1, required = false)
	private NetUserData user;

	public void setuser(NetUserData user){
		this.user=user;
	}
	public NetUserData getuser(){
		return user;
	}
}
