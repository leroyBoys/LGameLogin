package com.lgame.model.net;
import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;


/** 
 * 
 * auto class not modify 
 * 
 */
public class NetChat {
	@Protobuf(fieldType = FieldType.INT32, order =  1, required = false)
	private int receiveId;
	@Protobuf(fieldType = FieldType.STRING, order =  2, required = false)
	private String userName;
	@Protobuf(fieldType = FieldType.INT32, order =  3, required = false)
	private int channel;
	@Protobuf(fieldType = FieldType.STRING, order =  4, required = false)
	private String content;
	@Protobuf(fieldType = FieldType.INT32, order =  5, required = false)
	private int isaction;

	public void setreceiveId(int receiveId){
		this.receiveId=receiveId;
	}
	public int getreceiveId(){
		return receiveId;
	}
	public void setuserName(String userName){
		this.userName=userName;
	}
	public String getuserName(){
		return userName;
	}
	public void setchannel(int channel){
		this.channel=channel;
	}
	public int getchannel(){
		return channel;
	}
	public void setcontent(String content){
		this.content=content;
	}
	public String getcontent(){
		return content;
	}
	public void setisaction(int isaction){
		this.isaction=isaction;
	}
	public int getisaction(){
		return isaction;
	}
}
