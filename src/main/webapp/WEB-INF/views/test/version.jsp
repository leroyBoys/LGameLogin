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


<body>
<div  id="content">

	<table class="gridtable">
		<script type="text/javascript" >
			function getVerion() {
				var url = formatUrl("/gameserver/version");

				var data = jsonStr({ version: "0.1.1"
					, gameId: $("#gameId").val()
					, srcId: $("#srcId").val()
				});

				$.post(url,{
							data:data
						}
						,function(data){
							$("#target2").val(jsonStr(data));
						});
			}


			function login() {
				//登录
				var url =  formatUrl("/gameserver/login");
				var data = jsonStr({ userName: "tome"
					, pwd: "sdfsdf"
					,dev:{
						plat:"iphone",
						udid:"xxddsp12",
						mac:"01-5953-xa",
						info:"testinfo"
					}
				}); //转JSON字符串
				$.post(url,
						{ data: data
						} ,function(s){
							$("#target2").val(jsonStr(s));
						});
			}

			function connect() {
				//登录
				var url =  formatUrl("/gm/first");
				var data = { uid: $("#uid").val()
					, cmd: 2,
					msg: $("#target1").val(),
					key: $("#key").val(),
					serverId: $("#serverId").val(),
				}; //转JSON字符串

				console.log(data);
				$.post(url,data ,function(s){
					$("#target2").val(jsonStr(s));
				});
			}

			function getJsonsss() {
				//登录
				var url =  formatUrl("/gm/testmsg");
				var data = { uid: $("#uid").val(),
					module:$("#module").val()
					, cmd: $("#cmd").val(),
					key: ""
				}; //转JSON字符串


				$.post(url,data ,function(s){
					$("#target1").val(jsonStr(s));
				});
			}
		</script>

		<tr>
			<td>注意：</td><td>jsonStr(obj) 返回json字符串;key为空的时候获得相应cmd的json</td>
		</tr>
		<tr>
			<td><input value="执行内容1代码" type="button"  onclick="doNow()"/></td>
			<td><input value="获取version" type="button"  onclick="getVerion()"/>
				<input value="登录" type="button"  onclick="login()"/>
				<input value="连接服务器" type="button"  onclick="connect()"/>
				<input value="获得json" type="button"  onclick="getJsonsss()"/>
			</td>
		</tr>
		<tr>
			<td>srcId:</td><td><input type="text" id = "srcId" value="1"/>  gameId:<input type="text" value="1	" id = "gameId"/></td>
		</tr>
		<tr>
			<td>cmd:</td><td><input type="text" id = "cmd"/>  module: <input type="text" id = "module"/> </td>
		</tr>

		<tr>
			<td>uid:</td><td> <input type="text" id="uid"/>   serverId:<input type="text" id="serverId"/></td>
		</tr>

		<tr>
			<td>key:</td><td><input type="text" id = "key"/></td>
		</tr>
		<tr>
			<td>id:</td><td><input type="text" id = "id"/></td>
		</tr>
		<tr>
			<td>内容1:</td><td><textarea rows="15" cols="150"  id="target1">

		</textarea></td>
		</tr>
		<tr>
			<td>返回:</td><td><textarea rows="10" cols="150"  id="target2">

		</textarea></td>
		</tr>


	</table>

</div>
</body>
</html>
