package com.lgame.model.net;
import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;


/** 
 * 
 * auto class not modify 
 * 
 */
public class RQVote {
	@Protobuf(fieldType = FieldType.BOOL, order =  1, required = false)
	private boolean isagree;
	@Protobuf(fieldType = FieldType.INT32, order =  2, required = false)
	private int uid;

	public void setisagree(boolean isagree){
		this.isagree=isagree;
	}
	public boolean getisagree(){
		return isagree;
	}
	public void setuid(int uid){
		this.uid=uid;
	}
	public int getuid(){
		return uid;
	}
}
