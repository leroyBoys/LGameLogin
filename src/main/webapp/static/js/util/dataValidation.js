/**
 * 数据验证框架.
 */
var dataValidation = new function() {
	var idExt="_wangzi6hao_Span";//生成span层的id后缀
	/**
	 * @description 过滤所有空格字符。
	 * @param str:需要去掉空间的原始字符串
	 * @return 返回已经去掉空格的字符串
	 */
	this.trimSpace = function(str) {
		str += "";
		while ((str.charAt(0) == ' ') || (str.charAt(0) == '???')
		|| (escape(str.charAt(0)) == '%u3000'))
			str = str.substring(1, str.length);
		while ((str.charAt(str.length - 1) == ' ')
		|| (str.charAt(str.length - 1) == '???')
		|| (escape(str.charAt(str.length - 1)) == '%u3000'))
			str = str.substring(0, str.length - 1);
		return str;
	}
	/**
	 * 过滤字符串开始部分的空格\字符串结束部分的空格\将文字中间多个相连的空格变为一个空格
	 *
	 * @param {Object}
	 * inputString
	 */
	this.trim = function(inputString) {
		if (typeof inputString != "string") {
			return inputString;
		}
		var retValue = inputString;
		var ch = retValue.substring(0, 1);
		while (ch == " ") {
			// 检查字符串开始部分的空格
			retValue = retValue.substring(1, retValue.length);
			ch = retValue.substring(0, 1);
		}
		ch = retValue.substring(retValue.length - 1, retValue.length);
		while (ch == " ") {
			// 检查字符串结束部分的空格
			retValue = retValue.substring(0, retValue.length - 1);
			ch = retValue.substring(retValue.length - 1, retValue.length);
		}
		while (retValue.indexOf(" ") != -1) {
			// 将文字中间多个相连的空格变为一个空格
			retValue = retValue.substring(0, retValue.indexOf(" "))
				+ retValue.substring(retValue.indexOf(" ") + 1,
					retValue.length);
		}
		return retValue;
	}
	/**
	 * 过滤字符串,指定过滤内容，如果内容为空，则默认过滤 '~!@#$%^&*()-+."
	 *
	 * @param {Object}
	 * str
	 * @param {Object}
	 * filterStr
	 *
	 * @return 包含过滤内容，返回True,否则返回false;
	 */
	this.filterStr = function(str, filterString) {
		filterString = filterString == "" ? "'~!@#$%^&*()-+.\"" : filterString
		var ch;
		var i;
		var temp;
		var error = false;// 当包含非法字符时，返回True
		for (i = 0; i <= (filterString.length - 1); i++) {
			ch = filterString.charAt(i);
			temp = str.indexOf(ch);
			if (temp != -1) {
				error = true;
				break;
			}
		}
		return error;
	}
	this.filterStrSpan = function(id, filterString) {
		filterString = filterString == "" ? "'~!@#$%^&*()-+.\"" : filterString
		var val = document.getElementById(id);
		if (this.filterStr(val.value, filterString)) {
			val.select();
			var str = "不能包含非法字符" + filterString;
			this.appendError(id, str);
			return false;
		} else {
			this.remove(id + idExt);
			return true;
		}
	}
	/**
	 * 检查是否为网址
	 *
	 * @param {}
	 * str_url
	 * @return {Boolean} true：是网址，false:<b>不是</b>网址;
	 */
	this.isURL = function(str_url) {// 验证url
		var strRegex = "^((https|http|ftp|rtsp|mms)?://)"
			+ "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" // ftp的user@
			+ "(([0-9]{1,3}\.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184
			+ "|" // 允许IP和DOMAIN（域名）
			+ "([0-9a-z_!~*'()-]+\.)*" // 域名- www.
			+ "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\." // 二级域名
			+ "[a-z]{2,6})" // first level domain- .com or .museum
			+ "(:[0-9]{1,4})?" // 端口- :80
			+ "((/?)|" // a slash isn't required if there is no file name
			+ "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";
		var re = new RegExp(strRegex);
		return re.test(str_url);
	}
	this.isURLSpan = function(id) {
		var val = document.getElementById(id);
		if (!this.isURL(val.value)) {
			val.select();
			var str = "链接不符合格式;";
			this.appendError(id, str);
			return false;
		} else {
			this.remove(id + idExt);
			return true;
		}
	}
	/**
	 * 检查是否为电子邮件
	 *
	 * @param {}
	 * str
	 * @return {Boolean} true：电子邮件，false:<b>不是</b>电子邮件;
	 */
	this.isEmail = function(str) {
		var re = /^([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
		return re.test(str);
	}
	this.isEmailSpan = function(id) {
		var val = document.getElementById(id);
		if (!this.isEmail(val.value)) {
			val.select();
			var str = "邮件不符合格式;";
			this.appendError(id, str);
			return false;
		} else {
			this.remove(id + idExt);
			return true;
		}
	}
	/**
	 * 检查是否为整型
	 *
	 * @param {}
	 * str
	 * @return {Boolean} true：数字，false:<b>不是</b>数字;
	 */
	this.isNum = function(str) {
		if(!str) return false;
		var strP=/^-?\d+$/; //整数
//		  var strP=/^\d+$/; //正整数
		if(!strP.test(str)) return false;
		return true;
	}
	this.isNumSpan = function(id) {
		var val = document.getElementById(id);
		if (!this.isNum(val.value)) {
			val.select();
			var str = "必须是数字;";
			this.appendError(id, str);
			return false;
		} else {
			this.remove(id + idExt);
			return true;
		}
	}
	/**
	 * 检查是否为日期
	 *
	 * @param {}
	 * str 日期格式1999-10-10 10:20:20 /1999-10-10
	 * @return {Boolean} true：数字，false:<b>不是</b>数字;
	 */
	this.isDate = function(str) {
		var re = /^((((1[6-9]|[2-9]\d)\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\d|3[01]))|(((1[6-9]|[2-9]\d)\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\d|30))|(((1[6-9]|[2-9]\d)\d{2})-0?2-(0?[1-9]|1\d|2[0-8]))|(((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))(\s(([01]\d{1})|(2[0123])):([0-5]\d):([0-5]\d))?$/;
		var re2 = /^((((1[6-9]|[2-9]\d)\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\d|3[01]))|(((1[6-9]|[2-9]\d)\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\d|30))|(((1[6-9]|[2-9]\d)\d{2})-0?2-(0?[1-9]|1\d|2[0-8]))|(((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))(\s(([01]\d{1})|(2[0123]))-([0-5]\d)-([0-5]\d))?$/;
		return re.test(str) || re2.test(str) ;
	}
	/**
	 * 检查是否为浮点数
	 *
	 * @param {}
	 * str
	 * @return {Boolean} true：数字，false:<b>不是</b>数字;
	 */
	this.isDouble = function(str) {
		if(!str) return false;
//		 var strP=/^\d+(\.\d+)?$/;
		var strP=/^(-?\d+)(\.\d+)?$/;
		if(!strP.test(str)) return false;
		try{
			if(parseFloat(str)!=str) return false;
		}catch(ex){
			return false;
		}
		return true;
	}
	/**
	 * 检查是否为boolean
	 *
	 * @param {}
	 * str
	 * @return {Boolean} true：数字，false:<b>不是</b>数字;
	 */
	this.isBoolean = function(str) {
		if(str == "true"){
			return true;
		}
		return false;
	}
	/**
	 * 检查是否为数字含浮点数
	 *
	 * @param {}
	 * str
	 * @return {Boolean} true：数字，false:<b>不是</b>数字;
	 */
	this.isNumOrFloat = function(str) {
		return this.isNum(str) || this.isDouble(str);
	}
} 
