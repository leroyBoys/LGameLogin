package com.crystalcg.gamedev.utils;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 通用数据格式转换类
 *
 * @author lxh/2013-9-12/下午17:30:45
 */
public class DataFormat {

	/**
	 * 将json格式数据转换成map的list集合
	 * @param jsonArray
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String,Object>> fromJsonToMapList(JSONArray jsonArray){
		List<Map<String,Object>> jsonList = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = null;
		Iterator<JSONObject> it = jsonArray.iterator();
		while(it.hasNext()) {
			JSONObject jsonMap =  it.next();
			Iterator<String> its = jsonMap.keys();
			map = new HashMap<String, Object>();
			while(its.hasNext()){
				String key = (String) its.next();
				map.put(key, jsonMap.get(key));
			}
			jsonList.add(map);
		}
		return jsonList;
	}
	/**
	 * 对js转码后的字符串解码
	 * @param str
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String decodeString(String str) throws UnsupportedEncodingException{
		if(str == null || str.trim().isEmpty()){
			return "";
		}
		return java.net.URLDecoder.decode(str, "UTF-8");
	}
	/**
	 * 对js转码后的Map的value统一解码
	 * @param str
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static Map<String,Object> decodeMap(Map<String,Object> map) throws UnsupportedEncodingException{
		if(map == null || map.isEmpty()){
			return new HashMap<String,Object>();
		}
		for(String str:map.keySet()){
			map.put(str, decodeString((String)map.get(str)));
		}
		return map;
	}
}
