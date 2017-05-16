<%@page import="com.lgame.utils.PropertiesUtils"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.lgame.util.file.FileTool" %>
<%@ page import="com.lgame.util.file.ReadUpdateFile" %>
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
<div  id="content" >
	<div>
		<div id="logContent" rel="${logType}">
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function(){
			function checkLog() {

				if($("#logContent").length >0){
					var url =  formatUrl("/gm/log");
					setTimeout(function () {
						$.get(url,{
							type:$("#logContent").attr("rel")
						} ,function(slist){
							console.log(slist);

							if(slist == null || slist.length == 0){
								console.log("======>empty");
								checkLog();
								return
							}
							for(var line in slist){
								$("#logContent").append("<p>"+slist[line]+"</p>");
							}
							checkLog();
						});

					} , 2000);
				}
			}

			setTimeout(function () {
				checkLog();
			} , 1500);
		});
	</script>

</div>
</body>
</html>
