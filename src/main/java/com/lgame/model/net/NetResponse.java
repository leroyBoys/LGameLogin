package com.lgame.model.net;
import java.util.*;
import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;


/** 
 * 
 * auto class not modify 
 * 
 */
public class NetResponse {
	@Protobuf(fieldType = FieldType.INT32, order =  1, required = false)
	private int status;
	@Protobuf(fieldType = FieldType.OBJECT, order =  2, required = false)
	private List<NetOprateData> operateDatas;
	@Protobuf(fieldType = FieldType.INT32, order =  3, required = true)
	private int retStatus;
	@Protobuf(fieldType = FieldType.INT32, order =  4, required = false)
	private int step;

	public void setstatus(int status){
		this.status=status;
	}
	public int getstatus(){
		return status;
	}
	public void setoperateDatas(List<NetOprateData> operateDatas){
		this.operateDatas=operateDatas;
	}
	public List<NetOprateData> getoperateDatas(){
		return operateDatas;
	}
	public void setretStatus(int retStatus){
		this.retStatus=retStatus;
	}
	public int getretStatus(){
		return retStatus;
	}
	public void setstep(int step){
		this.step=step;
	}
	public int getstep(){
		return step;
	}
}
