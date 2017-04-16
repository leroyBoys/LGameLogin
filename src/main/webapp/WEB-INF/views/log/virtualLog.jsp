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
                          onHeaderContextMenu:onHeaderContextMenu,
                          toolbar:'#tb${random }'
                          ">
	      <thead>
			<tr>
			    <th data-options="field:'id',sortable:'true',hidden:true"><a title="id">id</a></th>
			     <th data-options="field:'character_id',align:'left',styler:cellStyler,sortable:'true'">君主ID</th>
			    <th data-options="field:'character_name',align:'left',styler:cellStyler,sortable:'true'">君主名字</th>
                <th data-options="field:'operate_no',align:'left',editor:'text',styler:cellStyler,sortable:'true'"><a title="操作单元">操作编号</a></th>
                <th data-options="field:'type',align:'left',editor:'text',styler:cellStyler,sortable:'true'"><a title="1进-1出0无影响">进出状态</a></th> 
                <th data-options="field:'reason_type' ,align:'left',editor:'text',styler:cellStyler,sortable:'true'"><a title="主模块">游戏模块</a></th>
                <th data-options="field:'reason_subType',align:'left',editor:'number',styler:cellStyler,sortable:'true'"><a title="功能模块">单元功能模块</a></th>
                <th data-options="field:'food',align:'left',sortable:'true'">粮食</th>              		
                <th data-options="field:'wood',align:'left',sortable:'true'">木材</th>              		
                <th data-options="field:'stone',align:'left',sortable:'true'">石料</th>              		
                <th data-options="field:'ironore',align:'left',sortable:'true'">铁矿</th>              		
                <th data-options="field:'money',align:'left',sortable:'true'">铜币</th>              		
                <th data-options="field:'ticket',align:'left',sortable:'true'">点卷</th>              		
                <th data-options="field:'cash',align:'left',sortable:'true'">金锭</th>              		
                <th data-options="field:'message',align:'left'">日志备注</th>              		
                <th data-options="field:'item_position',align:'left'">道具所在状态</th>              		
                <th data-options="field:'date',align:'left',sortable:'true'">产生日志日期</th>              		
			</tr>
		</thead>
	</table>
	<div id = "mm${random }" class="easyui-menu" style="padding:5px;height:auto;width:100px">
		<div onClick="viewMessage()" data-options="iconCls:'icon-edit'">查看</div> 
		<div onClick="removeSingleData(this)" delurl="${delUrl }" data-options="iconCls:'icon-remove'">删除</div>
		<div onClick="refreshTable('${url}')" data-options="iconCls:'icon-reload'">刷新</div> 
	</div>
	  <div id="tb${random }" style="padding:5px;height:auto">
          <div id="tb${random }" style="padding:5px;height:auto">
        <div style="margin-bottom:5px">
        	   操作编号:
			<input  type="text" class="easyui-validatebox" name="log_operate_no" style="width:100px"/>
			玩家ID:
			<input type="text" class="easyui-validatebox" name="log_character_id" style="width:100px"/>
			玩家名字(<a href="#" title="玩家可能已经更改名字，此项只用于查看玩家用此名字时的操作历史">历史</a>):
			<input type="text" class="easyui-validatebox" name="log_character_name" style="width:100px"/>
      		<select  name="log_type"   style="width:auto">
	      			<option value="" >全部</option>
	      			<option value="1" >得到</option>
	      			<option value="-1" >失去</option>
	      			<option value="0" >无影响</option>
			</select>
      		<select  name="log_reason_type"   style="width:auto">
	      			<option value="" >全部</option>
      				<c:forEach items="${functionArea }" var="area">
	      				<option value="${area.key }" >${area.value }</option>
      				</c:forEach>	
			</select>
      		<select  name="log_reason_subType"   style="width:auto">	
	      			<option value="" >全部</option>
      				<c:forEach items="${function }" var="f">
	      				<option value="${f.key }" >${f.value }</option>
      				</c:forEach>
			</select>
			时间区间:  
      		<input class="easyui-datetimebox"  data-options="formatter:myformatter" name="log_startTime" style="width:150px">
      		至:<input class="easyui-datetimebox" data-options="formatter:myformatter" name="log_endTime" style="width:150px"></input>
      		<a href="#" class="easyui-linkbutton equipment_search" cur_url="${url}" iconCls="icon-search" onclick="searchResult(this,'log_')" >查询</a>
        </div>
	</div>
	</div>
</div>
</body>
</html>