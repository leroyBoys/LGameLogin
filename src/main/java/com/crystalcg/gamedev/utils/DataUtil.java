package com.crystalcg.gamedev.utils;

/**
 * 自定义数据类
 *
 * @author lxh/2013-8-28/下午04:30:45
 */
public class DataUtil {
	private static String[] intArray = new String[]{"int","bit"};
	private static String[] doubleArray = new String[]{"float","numeric","decimal","double"};
	private static String[] dateArray = new String[]{"date","time","year"};
	private static String[] booleanArray = new String[]{"bool"};
	/**
	 * 根据数据库读出的数据格式统一自定义数据类型
	 * @param type
	 * @return
	 */
	public static String getTypeBySqlDataType(String type){
		type = type.toLowerCase();
		for(String i : intArray){
			if(type.contains(i)){
				return "number";
			}
		}
		for(String i : doubleArray){
			if(type.contains(i)){
				return "double";
			}
		}
		for(String i : dateArray){
			if(type.startsWith(i)){
				return "date";
			}
		}
		for(String i : booleanArray){
			if(type.contains(i)){
				return "boolean";
			}
		}
		return "text";
	}
	public static boolean verifyData(String dataType, String num) {
		if("number".equals(dataType)){
			if(!StringUtil.isNumber(num)){
				return false;
			}
		}else if("double".equals(dataType)){
			if(!StringUtil.isFloat(num)){
				return false;
			}
		}else if("date".equals(dataType)){
			if(!StringUtil.isDate(num)){
				return false;
			}
		}else if("boolean".equals(dataType)){
			if(!StringUtil.isBoolean(num)){
				return false;
			}
		}
		return true;
	}
}
