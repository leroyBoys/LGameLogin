package com.lgame.model.net;
import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;


/** 
 * 
 * auto class not modify 
 * 
 */
public class RQUserStatus {
	@Protobuf(fieldType = FieldType.INT32, order =  1, required = false)
	private int status;

	public void setstatus(int status){
		this.status=status;
	}
	public int getstatus(){
		return status;
	}
}
