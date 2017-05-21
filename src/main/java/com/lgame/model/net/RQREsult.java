package com.lgame.model.net;
import java.util.*;
import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;


/** 
 * 
 * auto class not modify 
 * 
 */
public class RQREsult {
	@Protobuf(fieldType = FieldType.INT32, order =  1, required = false)
	private int flag;
	@Protobuf(fieldType = FieldType.OBJECT, order =  2, required = false)
	private List<NetMjUserResult> users;

	public void setflag(int flag){
		this.flag=flag;
	}
	public int getflag(){
		return flag;
	}
	public void setusers(List<NetMjUserResult> users){
		this.users=users;
	}
	public List<NetMjUserResult> getusers(){
		return users;
	}
}
