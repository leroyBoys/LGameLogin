<%@page import="com.crystalcg.gamedev.utils.enums.ItemType"%>
<%@page import="com.crystalcg.gamedev.utils.enums.MailItemType"%>
<%@ include file="../common/base.jsp" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${basePath }/static/tool/easyUI/themes/default/easyui.css"/>
<link rel="stylesheet" type="text/css" href="${basePath }/static/tool/easyUI/themes/icon.css"/>
<link rel="stylesheet" type="text/css" href="${basePath }/static/css/style.css"/>
<script type="text/javascript" src="${basePath }/static/js/util/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${basePath }/static/js/util/dataValidation.js"></script>
<script type="text/javascript" src="${basePath }/static/js/util/tool.js"></script>
<script type="text/javascript" src="${basePath }/static/tool/easyUI/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${basePath }/static/js/main.js"></script>
<script type="text/javascript" src="${basePath }/static/js/addMoney.js"></script>
</head>
<body >
<div style="padding:10px;" id="content">
  	<div class="easyui-panel content"  style="width:auto:">
		<div style="padding:10px 0 10px 60px;">
	    	<table>
	    		<c:forEach items="${item }" var="t">
	    			<tr>
	    			<td>${t.key }:</td>
	    			<td>${t.value}</td>
	    			</tr>
	    		</c:forEach>
	    	</table>
	    </div>
	    <div>
	</div>
</div>
</div>
</body>
</html>