<%@page import="com.lgame.utils.PropertiesUtils"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<c:set var="basePath" value="${pageContext.request.scheme}://${pageContext.request.serverName }:${pageContext.request.serverPort}${path }"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>北京水晶石游戏管理系统登陆</TITLE></HEAD>
<link rel="stylesheet" type="text/css" href="${basePath}/static/css/User_Login.css"/>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<style type="text/css">
BODY{
	PADDING-RIGHT: 0px; PADDING-LEFT: 0px; BACKGROUND: url(${basePath}/static/images/user_all_bg.gif) #226cc5 repeat-x 50% top; PADDING-BOTTOM: 0px; MARGIN: 110px 0px 0px; FONT: 12px/150% Arial, "宋体" ,Helvetica,sans-serif; PADDING-TOP: 0px; TEXT-DECORATION: none
}
</style>
<body>
<form action="${basePath }/logined" id="ff" method="post" autocomplete="off">
	<div></div>
	<div id=user_login>
		<dl>
			  <dd id=user_top>
			  	<ul>
				    <li class=user_top_l></li>
				    <li class=user_top_c></li>
				    <li class=user_top_r></li>
			    </ul>
			  </dd>
			  <dd id=user_main>
			  <ul>
			    <li class=user_main_l></li>
			    <li class=user_main_c>
				    <div class="user_main_box">
						<ul>
							<li class="user_main_text">用户名:</li>
							<li class="user_main_input">
								<input class="txtusernamecssclass" type="text" name="name" autocomplete="off"/>
							</li>
						</ul>
						<ul>
							<li class="user_main_text">密码:</li>
							<li class="user_main_input">
								<input class="txtpasswordcssclass" type="password" name="password"   autocomplete="off"/>
							</li>
						</ul>
						<c:if test="${not empty msg }">
						<ul>
							<li class="user_main_text"></li>
							<li class="user_main_input" style="color: red">
								${msg }
								<c:remove var="msg" />
							</li>
						</ul>
						</c:if>
					</div>
			     </li>
			     <li class=user_main_r><input class=ibtnentercssclass id=ibtnenter style="border-top-width: 0px; border-left-width: 0px; border-bottom-width: 0px; border-right-width: 0px" onclick='javascript:webform_dopostbackwithoptions(new webform_postbackoptions("ibtnenter", "", true, "", "", false, false))' type=image src="${basePath}/static/images/user_botton.gif" name=ibtnenter> </li>
			   </ul>
			   </dd>
			   <dd id=user_bottom>
				 	<ul>
					    <li class=user_bottom_l></li>
					    <li class=user_bottom_c></li>
					    <li class=user_bottom_r></li>
				    </ul>
			    </dd>
		    </dl>
    </div>
    <span id=valrusername style="display: none; color: red"></span>
    <span id=valrpassword style="display: none; color: red"></span>
    <span id=valrvalidatecode style="display: none; color: red"></span>
	<div id=validationsummary1 style="display: none; color: red"></div>
	<div></div>
	</form>
	</body>
</html>
