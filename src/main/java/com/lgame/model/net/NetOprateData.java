package com.lgame.model.net;
import java.util.*;
import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;


/** 
 * 
 * auto class not modify 
 * 
 */
public class NetOprateData {
	@Protobuf(fieldType = FieldType.INT32, order =  1, required = false)
	private int otype;
	@Protobuf(fieldType = FieldType.INT32, order =  2, required = false)
	private int uid;
	@Protobuf(fieldType = FieldType.INT32, order =  3, required = false)
	private int dval;
	@Protobuf(fieldType = FieldType.INT32, order =  4, required = false)
	private int flag;
	@Protobuf(fieldType = FieldType.INT32, order =  5, required = false)
	private List<Integer> dlist;
	@Protobuf(fieldType = FieldType.OBJECT, order =  6, required = false)
	private List<NetKvData> kvDatas;

	public void setotype(int otype){
		this.otype=otype;
	}
	public int getotype(){
		return otype;
	}
	public void setuid(int uid){
		this.uid=uid;
	}
	public int getuid(){
		return uid;
	}
	public void setdval(int dval){
		this.dval=dval;
	}
	public int getdval(){
		return dval;
	}
	public void setflag(int flag){
		this.flag=flag;
	}
	public int getflag(){
		return flag;
	}
	public void setdlist(List<Integer> dlist){
		this.dlist=dlist;
	}
	public List<Integer> getdlist(){
		return dlist;
	}
	public void setkvDatas(List<NetKvData> kvDatas){
		this.kvDatas=kvDatas;
	}
	public List<NetKvData> getkvDatas(){
		return kvDatas;
	}
}
