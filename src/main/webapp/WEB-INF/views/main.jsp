<%@ include file="common/base.jsp" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${basePath }/static/tool/easyUI/themes/default/easyui.css"/>
<link rel="stylesheet" type="text/css" href="${basePath }/static/tool/easyUI/themes/icon.css"/>
<link rel="stylesheet" type="text/css" href="${basePath }/static/css/style.css"/>
<script type="text/javascript" src="${basePath }/static/js/util/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${basePath }/static/js/util/jquery.json-2.4.min.js"></script>
<script type="text/javascript" src="${basePath }/static/js/util/tool.js"></script>
<script type="text/javascript" src="${basePath }/static/js/util/dataValidation.js"></script>
<script type="text/javascript" src="${basePath }/static/tool/easyUI/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${basePath }/static/js/main.js"></script>
<script type="text/javascript" src="${basePath }/static/js/dynamic.js"></script>
<script type="text/javascript" src="${basePath }/static/js/addMoney.js"></script>
	<script type="text/javascript" src="${basePath }/static/js/gm.js"></script>
	<script type="text/javascript" src="${basePath }/static/js/logUpdate.js" charset="GBK"></script>
<title>北京飞羽游戏管理系统</title>
<script type="text/javascript">
  addStaticDb();
</script>
</head>
<body class="easyui-layout">

		<%@ include file="common/north.jsp" %>
		<%@ include file="common/south.jsp" %>
		<div data-options="region:'east',split:true,collapsed:true" title="资料汇总" style="width:100px;padding:10px;">
			<%-- <ul class="easyui-tree" data-options="url:'tree_data1.json',animate:true,dnd:true"></ul>
			
			--%>
		</div>
		<%@ include file="common/west.jsp" %>
		<div region="center"  border="false">
			<div id="tabs" class="easyui-tabs" fit="true" border="false" plain="true">
<!-- 			    <table id="grid_id"></table> -->
<!-- 			     <div id="gridPager"></div> -->
				<div title="欢迎进入飞羽游戏管理系统" href="${basePath }/default"></div>
			</div>
		</div>

</body>
</html>
