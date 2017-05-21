package com.lgame.model.net;
import java.util.*;
import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;


/** 
 * 
 * auto class not modify 
 * 
 */
public class NetKvData {
	@Protobuf(fieldType = FieldType.INT32, order =  1, required = false)
	private int k;
	@Protobuf(fieldType = FieldType.INT32, order =  2, required = false)
	private int v;
	@Protobuf(fieldType = FieldType.INT32, order =  3, required = false)
	private List<Integer> dlist;

	public void setk(int k){
		this.k=k;
	}
	public int getk(){
		return k;
	}
	public void setv(int v){
		this.v=v;
	}
	public int getv(){
		return v;
	}
	public void setdlist(List<Integer> dlist){
		this.dlist=dlist;
	}
	public List<Integer> getdlist(){
		return dlist;
	}
}
