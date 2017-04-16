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
   <table id ="dg${random}" class="easyui-datagrid" 
            data-options="           
                          iconCls: 'icon-edit',
                          singleSelect:true,
                          collapsible:true,
                          url:'dynamic/getUserCharacterPagination', 
                          method:'get',
                          autoRowHeight:false,
                          pagination:true,
                          pageSize:10,
                          toolbar:'#tb${random}',
                          rowStyler: rowStyler,
                          onHeaderContextMenu:onHeaderContextMenu,
                          onRowContextMenu:onRowContextMenuAutoMenu
                          ">
	      <thead>
            <tr>
                <th data-options="field:'id',width:50,sortable:'true',align:'center'"><a title="id">ID</a></th>
                <%-- <th data-options="field:'account_id',width:80,align:'center',styler:cellStyler,sortable:'true'"><a title="account_id">所属账号</a></th>
                	--%>
                <th data-options="field:'name',width:80,align:'center',editor:'text',styler:cellStyler,sortable:'true'"><a title="name">君主名称</a></th>
                <th data-options="field:'gender',width:100,align:'center',editor:'text',styler:cellStyler,sortable:'true'"><a title="gender">性别</a></th> 
                <th data-options="field:'country_name',width:50,align:'center',editor:'text',styler:cellStyler,sortable:'true'"><a title="country_name">国家</a></th>
                <th data-options="field:'level',width:50,align:'center',editor:'number',styler:cellStyler,sortable:'true'"><a title="level">等级</a></th>
                <th data-options="field:'experience',width:50,align:'center',editor:'number',styler:cellStyler,sortable:'true'"><a title="experience">经验值</a></th>
                <th data-options="field:'cash',width:60,align:'center',editor:'number',styler:cellStyler,sortable:'true'"><a title="cash">充值金锭</a></th>
                <th data-options="field:'ticket',width:60,align:'center',editor:'number',styler:cellStyler,sortable:'true'"><a title="ticket">点卷</a></th>
<!--                 <th data-options="field:'alliance_id',width:60,align:'center',editor:'number',styler:cellStyler,sortable:'true'"><a title="alliance_id">联盟ID</a></th> -->
<!--                 <th data-options="field:'alliance_position',width:60,align:'center',editor:'text',styler:cellStyler,sortable:'true'"><a title="alliance_position">联盟职位</a></th>  -->
<!--                 <th data-options="field:'strategy_value',width:60,align:'center',editor:'text',styler:cellStyler,sortable:'true'"><a title="strategy_value">策略值</a></th>  -->
<!--                 <th data-options="field:'internal_affairs',width:60,align:'center',editor:'text',styler:cellStyler,sortable:'true'"><a title="internal_affairs">内政值</a></th>  -->
<!--                 <th data-options="field:'tech_point',width:60,align:'center',editor:'text',styler:cellStyler,sortable:'true'"><a title="tech_point">科技点数</a></th>  -->
                <th data-options="field:'isfreeze',width:60,align:'center',editor:'number',styler:cellStyler,sortable:'true'"><a title="is_freeze">君主状态</a></th> 
<!--                 <th data-options="field:'description',width:150,align:'center',editor:'text',styler:cellStyler,sortable:'true'"><a title="description">描述</a></th>  -->
<!--                 <th data-options="field:'login_time',width:120,align:'center',editor:'date',styler:cellStyler,sortable:'true'"><a title="login_time">登陆时间</a></th> -->
<!--                 <th data-options="field:'logout_time',width:120,align:'center',editor:'date',styler:cellStyler,sortable:'true'"><a title="logout_time">退出时间</a></th> -->
<!--                 <th data-options="field:'opt',width:120,align:'center',formatter:rowformater">操作</th> -->
            </tr>
        </thead>
	</table>
 <div id="tb${random}" style="padding:5px;height:auto">
        <div style="margin-bottom:5px">
             <span>君主名称：</span><input type="text" id="character_name" class="easyui-validatebox" name="character_name" data-options="required:true" value="" size=10/>
             <span>等级：</span><input type="text" id="character_level" class="easyui-validatebox" name="character_level" data-options="required:true"value="" size=10/>
             <span>国家：</span><select id="country" name ="character_country_id" class="easyui-combobox" panelHeight="auto" style="width:100px">
                                        <option value="7">请选择国家:</option>
                                        <option value="1">隋</option>
						                <option value="2">夏</option>
						                <option value="3">魏</option>
						                <option value="4">楚</option>
						                <option value="5">梁</option>
						                <option value="6">中立</option>
						                <option value="0">无</option>
                               </select>
             <span>状态：</span><select id="country" name ="character_is_freeze" class="easyui-combobox" panelHeight="auto" style="width:50px">
                                        <option value="3">全部</option>
                                        <option value="0">正常</option>
						                <option value="1">冻结</option>

                               </select>                 
                               
              <a href="#" class="easyui-linkbutton dynamic_db_search" cur_url="dynamic/getUserCharacterPagination" iconCls="icon-search" onclick='searchResult(this,"character_")'>查 询</a>
              
<!--               <a href="javascript:void(0)" class="easyui-linkbutton " delurl="update" onclick ="removeSingleData(this)" data-options="iconcls:'icon-remove',plain:true">删除</a> -->
<!--               <a href="javascript:void(0)" class="easyui-linkbutton " delurl="/dynamic/deleteUserCharacter" onclick ="removeSingleData(this)" data-options="iconcls:'icon-remove',plain:true">删除</a> -->
<!--              <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconcls:'icon-reload',plain:true" onclick="refreshTable('/dynamic/todynamic')">刷新</a> -->
        </div>
</div>
<div id="mm${random}" class="easyui-menu" style="padding:5px;height:auto;width:100px"> 
	<div id="view" onClick="Preview()" data-options="iconCls:'icon-search'">查看</div> 
	<div id="edit" onClick="edit()" delurl="" data-options="iconCls:'icon-remove'">freeze</div>
	<div onClick="refreshTable('/dynamic/todynamic')" data-options="iconCls:'icon-reload'">刷新</div>
</div>
</div>
</body>
</html>