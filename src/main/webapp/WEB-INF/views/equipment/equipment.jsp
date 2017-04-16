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
                          toolbar:'#tb${random }',
                          rowStyler: rowStyler,
                          onHeaderContextMenu:onHeaderContextMenu,
                          onRowContextMenu:onRowContextMenu
                          ">
	      <thead>
			<tr>
				<c:forEach items="${commentList }" var="comm" >
					<c:choose>
						<c:when test="${comm.name == 'id' }">
							<th data-options="field:'${comm.name}',hidden:true" style="font-weight: bold;"><a title="${comm.comment}">${comm.comment}</a></th>
						</c:when>
						<c:otherwise>
							<th data-options="field:'${comm.name}',sortable:'true',styler:cellStyler,align:'center'"  >
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
	<div id = "mm${random }" class="easyui-menu" style="padding:5px;height:auto;width:100px">
		<div onClick="modifyData()" data-options="iconCls:'icon-edit'">修改</div> 
		<div onClick="removeSingleData(this)" delurl="${delUrl }" data-options="iconCls:'icon-remove'">删除</div>
		<div onClick="refreshTable('${url}')" data-options="iconCls:'icon-reload'">刷新</div> 
	</div>
	  <div id="tb${random }" style="padding:5px;height:auto">
        <div style="margin-bottom:5px">
        	<select  name="equipment_role"   style="width:auto">
					<option value="0" >角色名字</option>
					<option value="1" >角色id</option>
			</select>
			<input   type="text" class="easyui-validatebox" name="equipment_character_id" style="width:100px"/>
        	<select  name="equipment_item"   style="width:auto">
					<option value="0" >道具名称</option>
					<option value="1" >道具编号</option>
			</select>
      		<input   type="text" class="easyui-validatebox" name="equipment_item_no" style="width:100px"/>
      		强化等级:
      		<input   type="text" class="easyui-numberbox" name="equipment_strength_level" style="width:100px"/>
      		道具位置:
      		<select  name="equipment_item_position"   style="width:auto">
					<option value="-1" >全部</option>
					<option value="0" >国库</option>
					<option value="1" >武将</option>
					<option value="2" >邮件</option>
			</select>
      		<a href="#" class="easyui-linkbutton equipment_search" cur_url="${url}" iconCls="icon-search" onclick="searchResult(this,'equipment_')" >查询</a>
			<%-- <a href="javascript:void(0)" class="easyui-linkbutton static_db_del" delurl="${delUrl }" data-options="iconCls:'icon-remove',plain:true" >删除</a>--%>
        </div>
	</div>
</div>


</body>
</html>