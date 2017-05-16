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
<body>
<div  id="content">
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

	<input type="hidden" id="uid"/>
	<input type="hidden" id = "key"/>
	<table class="gridtable">
		<tr>
			<td>注意：</td><td>jsonStr(obj) 返回json字符串;key为空的时候获得相应cmd的json</td>
		</tr>
		<tr>
			<td>srcId:</td><td><input type="text" id = "srcId" value="1"/>  gameId:<input type="text" value="1" id = "gameId"/></td>
		</tr>
		<tr>
			<td>serverId:</td><td>:<input type="text" id="serverId"/></td>
		</tr>
		<tr>
			<td><input value="获取version" type="button"  onclick="getVerion()"/></td>
			<td><textarea rows="2" cols="150"  id="versions"></textarea></td>
		</tr>

		<tr>
			<td>用户名:</td><td><input type="text" id = "userName"/> 密码：<input type="text" id = "userPwd"/>
			<input value="登录" type="button"  onclick="login()"/> 	<input value="连接服务器" type="button"  onclick="connect()"/> <label id="tipLable"></label></td>
		</tr>

		<tr>
			<td><input value="执行内容1代码" type="button"  onclick="doNow()"/></td>
			<td>
				<input value="获得json" type="button"  onclick="getJsonsss()"/>
				<input value="发送数据" type="button"  onclick="sendmsg()"/>
			</td>
		</tr>

		<tr>
			<td>module:</td><td> <input type="text" id = "module" value="0"/> cmd:<input type="text"  value="2" id = "cmd"/> </td>
		</tr>
		<tr>
			<td>内容1:</td><td><textarea rows="3" cols="150"  id="target1">

		</textarea></td>
		</tr>
		<tr>
			<td>返回:</td><td><textarea rows="3" cols="150"  id="target2">

		</textarea></td>
		</tr>


	</table>

</div>
</body>
</html>
