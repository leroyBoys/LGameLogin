<%@page import="com.lgame.utils.PropertiesUtils"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<c:set var="basePath" value="${pageContext.request.scheme}://${pageContext.request.serverName }:${pageContext.request.serverPort}${path }"></c:set>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/base.jsp" %>
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
	<TITLE>北京飞羽游戏管理系统登陆</TITLE>
</HEAD>
<link rel="stylesheet" type="text/css" href="${basePath }/static/tool/easyUI/themes/default/easyui.css"/>
<link rel="stylesheet" type="text/css" href="${basePath }/static/tool/easyUI/themes/icon.css"/>
<link rel="stylesheet" type="text/css" href="${basePath }/static/css/style.css"/>
<script type="text/javascript" src="${basePath }/static/js/util/jquery-1.8.0.min.js"></script>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<style type="text/css">
table.gridtable {

	font-family: verdana,arial,sans-serif;

	font-size:11px;

	color:#333333;

	border-width: 1px;

	border-color: #666666;

	border-collapse: collapse;

}

table.gridtable th {

	border-width: 1px;

	padding: 8px;

	border-style: solid;

	border-color: #666666;

	background-color: #dedede;

}

table.gridtable td {

	border-width: 1px;

	padding: 8px;

	border-style: solid;

	border-color: #666666;

	background-color: #ffffff;

}
</style>

<script type="text/javascript" >
	function jsonStr(obj){
		return JSON.stringify(obj); //转JSON字符串
	}

	function doNow() {
		console.log("--------------------->"+$("#target1").val());
		eval($("#target1").val());
	}

	function doNow2() {
		var url = "http://localhost:8080/gameserver/register";
		var data = JSON.stringify({ name: "0.1.0"
			, pwd: "1"
			, code: "2"
			,dev:{
				plat:"andorooid",
				udid:"xxdds0012",
				mac:"01-52-23-xa",
				info:"testinfo"
			}
		}); //转JSON字符串
		console.log("zhuanjson =======>"+data);

		$.ajax({
			url : url,
			type : "POST",
			data :data ,
			dataType: 'json',
			contentType:'application/json;charset=UTF-8',
			success : function(result) {
				console.log(result);
			}
		});
	}

</script>
<body>
<table class="gridtable">
	<tr>
		<td>内容1:</td><td><textarea rows="15" cols="150"  id="target1">

		</textarea></td>
	</tr>

	<tr>
		<td><input id="clear" value="执行第一个" type="button"  onclick="doNow()"/></td><td><input value="执行第二个" type="button" onclick="doNow2()"/></td>
	</tr>

</table>


</body>
</html>
