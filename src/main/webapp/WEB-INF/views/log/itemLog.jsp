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
            data-options="iconCls: 'icon-edit',
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
			    <th data-options="field:'id',sortable:'true'"><a title="id">id</a></th>
			    <th data-options="field:'character_id',align:'left',styler:cellStyler,sortable:'true'">君主ID</th>
			    <th data-options="field:'character_name',align:'left',styler:cellStyler,sortable:'true'">君主名字</th>
                <th data-options="field:'operate_no',align:'left',editor:'text',styler:cellStyler,sortable:'true'"><a title="操作单元">操作编号</a></th>
                <th data-options="field:'type',align:'left',editor:'text',styler:cellStyler,sortable:'true'"><a title="1进-1出0无影响">进出状态</a></th> 
                <th data-options="field:'reason_type' ,align:'left',editor:'text',styler:cellStyler,sortable:'true'"><a title="主模块">游戏模块</a></th>
                <th data-options="field:'reason_subType',align:'left',editor:'number',styler:cellStyler,sortable:'true'"><a title="功能模块">单元功能模块</a></th>
                <th data-options="field:'item_no',align:'left',formatter:function(value,row,index){return '<a onclick=itemView(\''+row.itemType+'\',\''+value+'\') href=\'#\'>'+value+'</a>'}"><a title="物品编号">物品编号</a></th> 
                <th data-options="field:'item_name',align:'left'"><a title="物品名称">物品名称</a></th>              		
                <th data-options="field:'itemType',align:'left',hidden:true"><a title="物品类型">物品类型</a></th>              		
                <th data-options="field:'item_type',align:'left'"><a title="物品类型">物品类型</a></th>              		
              <%--  <th data-options="field:'item_subType',align:'left'"><a title="物品子类型">物品子类型</a></th>              		
                <th data-options="field:'item_effect_type',align:'left'">消耗品类型</th>              		
                <th data-options="field:'item_id',align:'left',editor:'number',styler:cellStyler,sortable:'true'"><a title="物品id">物品id</a></th>              
                <th data-options="field:'item_quality',align:'left'">物品品质</th>      --%>         	 
                <th data-options="field:'item_num',align:'left',sortable:'true'">物品数量</th>              		
                <th data-options="field:'item_is_bop',align:'left'">绑定状态</th>              		
                <th data-options="field:'message',align:'left'">日志备注</th>              		
                <th data-options="field:'item_position',align:'left'">道具所在状态</th>              		
                <th data-options="field:'date',align:'left',sortable:'true'">产生日志日期</th>              		
			</tr>
		</thead>
	</table>
	  <div id="tb${random }" style="padding:5px;height:auto">
        <div style="margin-bottom:5px">
        	   操作编号:
			<input  type="text" class="easyui-validatebox" name="log_operate_no" style="width:100px"/>
			玩家ID:
			<input type="text" class="easyui-validatebox" name="log_character_id" style="width:100px"/>
			玩家名字(<a href="#" title="玩家可能已经更改名字，此项只用于查看玩家用此名字时的操作历史">历史</a>):
			<input type="text" class="easyui-validatebox" name="log_character_name" style="width:100px"/>
			道具编号:
			<input type="text" class="easyui-validatebox" name="log_item_no" style="width:100px"/>
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
</body>
</html>