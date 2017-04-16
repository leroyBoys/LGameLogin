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
<script type="text/javascript" src="${basePath }/static/js/dynamic.js"></script>
</head>
<body >
<div style="padding:10px;" id="content">
  <c:set var="random" value="${random == null ? 0:random+1}" scope="session"></c:set> 
   <table id ="dg${random }" class="easyui-datagrid" 
            data-options="rownumbers:true,           
                          iconCls: 'icon-edit',
                          singleSelect: true,
                          collapsible:true,
                          url:'${basePath }/${url}', 
                          method:'get',
                          autoRowHeight:false,
                          pagination:true,
                          pageSize:10,
                          toolbar:'#tb${random }',
                          rowStyler: rowStyler,
                          onHeaderContextMenu:onHeaderContextMenu,
                          onRowContextMenu:onRowContextMenu
                          ">
	      <thead>
			<tr>
			    <th data-options="field:'id',width:50,sortable:'true',hidden:true"><a title="id">ID</a></th>
			    <th data-options="field:'operator_id',width:50,align:'center',styler:cellStyler,sortable:'true'"><a title="operator_id">操作人</a></th>
                <th data-options="field:'addresser',width:50,align:'center',styler:cellStyler,sortable:'true'"><a title="addresser">发件人</a></th>
                <th data-options="field:'addressee',width:50,align:'center',styler:cellStyler,sortable:'true'"><a title="addressee">收件人</a></th>
                <th data-options="field:'title',width:100,align:'center',styler:cellStyler,sortable:'true'"><a title="title">邮件标题</a></th> 
                <th data-options="field:'content',width:300 ,align:'center',styler:cellStyler,sortable:'true'"><a title="content">邮件正文</a></th>
                <th data-options="field:'cash',width:80,align:'center',styler:cellStyler,sortable:'true'"><a title="cash">充值金锭</a></th>
                <th data-options="field:'send_time',width:130,align:'center',styler:cellStyler,sortable:'true'"><a title="send_time">发送时间</a></th>              
                <th data-options="field:'status',width:60,align:'center',formatter:formaterStatus"><a title="status">邮件状态</a></th> 
                <th data-options="field:'attachment1',width:60,align:'center', formatter:formaterIsAttach"><a title="attachment1">有无附件</a></th>              		
			</tr>
		</thead>
	</table>
	<div id = "mm${random }" class="easyui-menu" style="padding:5px;height:auto;width:100px">
		<div onClick="viewMessage()" data-options="iconCls:'icon-edit'">查看</div> 
		<div onClick="removeSingleData(this)" delurl="${delUrl }" data-options="iconCls:'icon-remove'">删除</div>
		<div onClick="refreshTable('${url}')" data-options="iconCls:'icon-reload'">刷新</div> 
	</div>
	  <div id="tb${random }" style="padding:5px;height:auto">
        <div style="margin-bottom:5px">
            操作人:
			<input  type="text" class="easyui-validatebox" name="email_operator_id" style="width:100px"/>
			收件人:
			<input type="text" class="easyui-validatebox" name="email_addressee" style="width:100px"/>
			邮件标题:
			<input type="text" class="easyui-validatebox" name="email_title" style="width:100px"/>
			发送时间:  
      		<input class="easyui-datetimebox"  data-options="formatter:myformatter" name="email_send_time" style="width:150px">
      		至:<input class="easyui-datetimebox" data-options="formatter:myformatter" name="email_tosend_time " style="width:150px"></input>
      		<select  name="email_status"   style="width:auto">	
      				<option value="2" >全部</option>
					<option value="1" >已读</option>
					<option value="0" >未读</option>
			</select>
      		<select  name="email_is_attach"   style="width:auto">	
      				<option value="2" >全部</option>
					<option value="1" >有附件</option>
					<option value="0" >无附件</option>
			</select>
         	<select  name="email_addresser"   style="width:auto">	
      				<option value="0" >全部</option>
					<option value="-1" >系统</option>
					<option value="-2" >拍卖行</option>
					<option value="1" >玩家</option>
			</select>
      		<a href="#" class="easyui-linkbutton equipment_search" cur_url="${url}" iconCls="icon-search" onclick="searchResult(this,'email_')" >查询</a>
        </div>
	</div>
</div>
</body>
</html>