package com.lgame.model.net;
import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;


/** 
 * 
 * auto class not modify 
 * 
 */
public class RQExit {
	@Protobuf(fieldType = FieldType.INT32, order =  1, required = false)
	private int uid;

	public void setuid(int uid){
		this.uid=uid;
	}
	public int getuid(){
		return uid;
	}
}
