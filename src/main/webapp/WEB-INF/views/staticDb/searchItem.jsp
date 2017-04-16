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
</head>
<body >
<div style="padding:10px;" id="content">
	<c:set var="random" value="${random == null ? 0:random+1}" scope="session"></c:set>
	<table id="dg${random}" class="easyui-datagrid" title="" style="width:auto;"
			data-options="
				title:'请选择道具',
				url:'${basePath }/${url}',
				onClickRow: onClickRow,
				pagination:true,
				pageSize:10,
				toolbar:'#tt${random}'
			">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'item_no',width:150"  >道具编号</th>
				<th data-options="field:'item_name',width:150"  >道具名称</th>
				<th data-options="field:'quality',width:150"  >道具品质</th>
				<th data-options="field:'num',editor:'numberbox',width:80,formatter:function(value){return $.trim(value) == '' ? 1:value}"  >数量</th>
			</tr>
		</thead>
	</table>
	<div  style="padding:5px;height:auto" id="tt${random}">
			道具名称
			<input   type="text" class="easyui-validatebox" name="search_item_name"  search_url="${url}" onkeyup="searchResult(this,'search_','#dg${random}')"/>
			<a href="#" class="easyui-linkbutton" iconCls="icon-button" onclick="closeWindow('#dg${random}')">确认选择</a>
	</div>
</div>
</body>
</html>
