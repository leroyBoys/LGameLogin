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
	<form action="${basePath}/upload" id="ff${random }" method="post" enctype="multipart/form-data">
		<input type="file" style="display:none" name="file" onchange="importDB('${tableName }')"/> 
	</form>
	<div  style="height:auto;">
			字段
			<select class="easyui-combobox" name="static_db_conditionName"  panelHeight="auto" style="width:auto">
				<c:forEach items="${commentList }" var="comm">
						<c:if test="${comm.name != 'id'}">
							<c:choose>
								<c:when test="${fn:length(comm.comment) > 8}">
									<option value="${comm.name}" >${fn:substring(comm.comment, 0,8)}...</option>
								</c:when>
								<c:when test="${fn:length(comm.comment) > 0}">
									<option value="${comm.name}">${comm.comment}</option>
								</c:when>
								<c:otherwise>
									<option value="${comm.name}">${comm.name}</option>
								</c:otherwise>	
							</c:choose>
						
						</c:if>
				</c:forEach>
			</select>
			内容
			<input   type="text" class="easyui-validatebox" name="static_db_conditionValue" data-options="required:true" />
			方式: 
			<select class="easyui-combobox" name="static_db_search_type"  panelHeight="auto"  style="width:100px">
				<option value="0">精确查询</option>
				<option value="1">模糊查询</option>
			</select>
			<a href="#" class="easyui-linkbutton static_db_search" search_url="${url}" iconCls="icon-search" onclick="searchResult(this,'static_db_')" >查询</a>
			<a href="javascript:void(0)" class="easyui-linkbutton static_db_add" data-options="iconCls:'icon-add',plain:true" onclick="appendData()">添加</a>
			<a href="javascript:void(0)" class="easyui-linkbutton static_db_del" delurl="${delUrl }" data-options="iconCls:'icon-remove',plain:true" onclick ='removeSingleData(this)' >删除</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true"  onclick ="$('#ff${random }').children('input:first').click()" >批量导入</a>
			<a href="javascript:void(0)" class="easyui-linkbutton static_db_save"  saveurl="${saveUrl }"  data-options="iconCls:'icon-save',plain:true">保存</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"  data-options="iconCls:'icon-save',plain:true"   onclick ="window.location.href='${toExcel }'">备份数据</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" onclick="refreshTable('${url}')">刷新</a>
		<%-- <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="getChanges()">GetChanges</a>
	--%>
	</div>
	<table id="dg${random}" class="easyui-datagrid" title="" style="width:auto;"
			data-options="
				iconCls: 'icon-edit',
				singleSelect: true,
				remoteSort:false,
				url:'${basePath }/${url}',
				onClickRow: onClickRow,
				pagination:true,
				loadFilter:pagerFilter,
				pageSize:10,
				rowStyler: rowStyler,
				onHeaderContextMenu:onHeaderContextMenu,
				onSortColumn:onSortColumn
			">
		<thead>
			<tr>
				<c:forEach items="${commentList }" var="comm"  varStatus="num">
					<c:choose>
						<c:when test="${comm.name == 'id' }">
							<th data-options="field:'${comm.name}'" style="font-weight: bold;"><a title="${comm.comment}">id</a></th>
						</c:when>
						<c:otherwise>
							<th data-options="field:'${comm.name}',editor:'${comm.dataType }',sortable:'true',styler:cellStyler,sorter:sorter"  >
								<c:choose>
									<c:when test="${comm.comment != ''}">
										<a title="${comm.comment}(${comm.name })">${comm.comment}</a>
									</c:when>
									<c:otherwise>
										<a title="${comm.name}">${comm.name}</a>
									</c:otherwise>	
								</c:choose>
							</th>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</tr>
		</thead>
		
	</table>
</div>
</body>
</html>
