package com.lgame.model.net;
import java.util.*;
import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;


/** 
 * 
 * auto class not modify 
 * 
 */
public class NetError {
	@Protobuf(fieldType = FieldType.INT32, order =  1, required = true)
	private int errorCode;
	@Protobuf(fieldType = FieldType.INT32, order =  2, required = false)
	private List<Integer> paramters;
	@Protobuf(fieldType = FieldType.INT32, order =  3, required = false)
	private int seq;

	public void seterrorCode(int errorCode){
		this.errorCode=errorCode;
	}
	public int geterrorCode(){
		return errorCode;
	}
	public void setparamters(List<Integer> paramters){
		this.paramters=paramters;
	}
	public List<Integer> getparamters(){
		return paramters;
	}
	public void setseq(int seq){
		this.seq=seq;
	}
	public int getseq(){
		return seq;
	}
}
