package com.lgame.model.net;
import java.util.*;
import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;


/** 
 * 
 * auto class not modify 
 * 
 */
public class NetMjUserResult {
	@Protobuf(fieldType = FieldType.OBJECT, order =  1, required = false)
	private List<NetKvData> scores;
	@Protobuf(fieldType = FieldType.INT32, order =  2, required = false)
	private int showType;
	@Protobuf(fieldType = FieldType.INT32, order =  3, required = false)
	private int winType;
	@Protobuf(fieldType = FieldType.INT32, order =  4, required = false)
	private int score;

	public void setscores(List<NetKvData> scores){
		this.scores=scores;
	}
	public List<NetKvData> getscores(){
		return scores;
	}
	public void setshowType(int showType){
		this.showType=showType;
	}
	public int getshowType(){
		return showType;
	}
	public void setwinType(int winType){
		this.winType=winType;
	}
	public int getwinType(){
		return winType;
	}
	public void setscore(int score){
		this.score=score;
	}
	public int getscore(){
		return score;
	}
}
