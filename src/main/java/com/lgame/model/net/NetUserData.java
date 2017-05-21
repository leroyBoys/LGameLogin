package com.lgame.model.net;
import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;


/** 
 * 
 * auto class not modify 
 * 
 */
public class NetUserData {
	@Protobuf(fieldType = FieldType.INT32, order =  1, required = false)
	private int uid;
	@Protobuf(fieldType = FieldType.STRING, order =  2, required = false)
	private String image;
	@Protobuf(fieldType = FieldType.INT32, order =  3, required = false)
	private int idex;
	@Protobuf(fieldType = FieldType.INT32, order =  4, required = false)
	private int status;
	@Protobuf(fieldType = FieldType.OBJECT, order =  5, required = false)
	private NetExtraData extra;

	public void setuid(int uid){
		this.uid=uid;
	}
	public int getuid(){
		return uid;
	}
	public void setimage(String image){
		this.image=image;
	}
	public String getimage(){
		return image;
	}
	public void setidex(int idex){
		this.idex=idex;
	}
	public int getidex(){
		return idex;
	}
	public void setstatus(int status){
		this.status=status;
	}
	public int getstatus(){
		return status;
	}
	public void setextra(NetExtraData extra){
		this.extra=extra;
	}
	public NetExtraData getextra(){
		return extra;
	}
}
