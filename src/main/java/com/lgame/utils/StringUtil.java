package com.lgame.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * 字符串工具类
 *
 * @author lxh/2013-8-13/下午04:30:45
 */
public class StringUtil {

	public static boolean isEmpty(String str){
		if(str == null || str.trim().isEmpty()){
			return true;
		}
		return false;
	}
	/**
	 * 将null转换成“”
	 * @param string
	 * @return
	 */
	public static String trimNull(String string) {
		if(string == null){
			return "";
		}
		return string;
	}
	/**
	 * 将“”转换成null
	 * @param str
	 * @return
	 */
	public static String trimEmpty(String str){
		if(str == null){
			return null;
		}else if(str.trim().equals("")){
			return null;
		}
		return str.trim();
	}
	public static String getStringFormatSql(String beforeStr,Object... obj) {
		if(obj.length == 0){
			return beforeStr;
		}
		String[] strHelp = beforeStr.split("\\?");
		StringBuilder temp = new StringBuilder();
		for(int i=0;i<obj.length;i++){
			temp.append(strHelp[i]).append(obj[i]);
		}
		temp.append(strHelp[strHelp.length-1]);
		return temp.toString();
	}
	public static boolean isBoolean(String obj){
		if("true".equalsIgnoreCase(obj) || "false".equalsIgnoreCase(obj)){
			return true;
		}
		return false;
	}
	public static boolean isNumber(String num){
		String eL = "^-?\\d+$";
        /*if(type.equals("0+"))eL = "^\\d+$";//非负整数
        else if(type.equals("+"))eL = "^\\d*[1-9]\\d*$";//正整数
        else if(type.equals("-0"))eL = "^((-\\d+)|(0+))$";//非正整数
        else if(type.equals("-"))eL = "^-\\d*[1-9]\\d*$";//负整数
        else eL = "^-?\\d+$";//整数  */
		Pattern p = Pattern.compile(eL);
		Matcher m = p.matcher(num);
		return m.matches();
	}
	/**
	 * 检查浮点数
	 * @param num
	 * @return
	 */
	public static boolean isFloat(String num){
		String eL = "^(-?\\d+)(\\.\\d+)?$";
        /*"^\\d+(\\.\\d+)?$";//非负浮点数
        "^((\\d+\\.\\d*[1-9]\\d*)|(\\d*[1-9]\\d*\\.\\d+)|(\\d*[1-9]\\d*))$";//正浮点数
        "^((-\\d+(\\.\\d+)?)|(0+(\\.0+)?))$";//非正浮点数
        "^(-((\\d+\\.\\d*[1-9]\\d*)|(\\d*[1-9]\\d*\\.\\d+)|(\\d*[1-9]\\d*)))$";//负浮点数
        "^(-?\\d+)(\\.\\d+)?$";//浮点数   */
		Pattern p = Pattern.compile(eL);
		Matcher m = p.matcher(num);
		return m.matches();
	}
	public static boolean isDate(String obj){
		Pattern pattern = Pattern.compile("(?:[0-9]{1,4}(?<!^0?0?0?0))-(?:0?[1-9]|1[0-2])-(?:0?[1-9]|1[0-9]|2[0-8]|(?:(?<=-(?:0?[13578]|1[02])-)(?:29|3[01]))|(?:(?<=-(?:0?[469]|11)-)(?:29|30))|(?:(?<=(?:(?:[0-9]{0,2}(?!0?0)(?:[02468]?(?<![13579])[048]|[13579][26]))|(?:(?:[02468]?[048]|[13579][26])00))-0?2-)(?:29)))");
		Matcher m = pattern.matcher(obj);
		if(obj == null || !m.matches()){
			return false;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-M-d");
		try {
			sdf.setLenient(false);
			obj = obj.replaceAll("-0", "-");
			Date d = sdf.parse(obj);
			return obj.equals(sdf.format(d));
		} catch (Exception e) {
		}
		return false;
	}
}
