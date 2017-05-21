package com.lgame.model.net;
import java.util.*;
import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;


/** 
 * 
 * auto class not modify 
 * 
 */
public class NetExtraData {
	@Protobuf(fieldType = FieldType.INT32, order =  1, required = false)
	private List<Integer> list;
	@Protobuf(fieldType = FieldType.OBJECT, order =  2, required = false)
	private List<NetKvData> kvDatas;
	@Protobuf(fieldType = FieldType.OBJECT, order =  3, required = false)
	private List<NetOprateData> operates;

	public void setlist(List<Integer> list){
		this.list=list;
	}
	public List<Integer> getlist(){
		return list;
	}
	public void setkvDatas(List<NetKvData> kvDatas){
		this.kvDatas=kvDatas;
	}
	public List<NetKvData> getkvDatas(){
		return kvDatas;
	}
	public void setoperates(List<NetOprateData> operates){
		this.operates=operates;
	}
	public List<NetOprateData> getoperates(){
		return operates;
	}
}
