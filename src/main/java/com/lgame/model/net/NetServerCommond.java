package com.lgame.model.net;
import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;


/** 
 * 
 * auto class not modify 
 * 
 */
public class NetServerCommond {
	@Protobuf(fieldType = FieldType.INT32, order =  1, required = true)
	private int uid;
	@Protobuf(fieldType = FieldType.BYTES, order =  2, required = true)
	private byte[] obj;
	@Protobuf(fieldType = FieldType.STRING, order =  3, required = false)
	private String sn;

	public void setuid(int uid){
		this.uid=uid;
	}
	public int getuid(){
		return uid;
	}
	public void setobj(byte[] obj){
		this.obj=obj;
	}
	public byte[] getobj(){
		return obj;
	}
	public void setsn(String sn){
		this.sn=sn;
	}
	public String getsn(){
		return sn;
	}
}
