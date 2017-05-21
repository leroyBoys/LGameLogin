package com.lgame.model.net;
import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;


/** 
 * 
 * auto class not modify 
 * 
 */
public class RPVote {
	@Protobuf(fieldType = FieldType.BOOL, order =  1, required = false)
	private boolean isagree;

	public void setisagree(boolean isagree){
		this.isagree=isagree;
	}
	public boolean getisagree(){
		return isagree;
	}
}
