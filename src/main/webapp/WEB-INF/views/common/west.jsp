<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div data-options="region:'west',split:true" title="管理菜单" style="width:150px;padding:0px;">
			<div id="menu" class="easyui-accordion" data-options="fit:true,border:false">
				<div title="游戏数据管理" id="staticDb" style="padding:0px;overflow: show">
				</div>
				<div title="用户信息管理"  id="dynamicDb" style="padding:0px;overflow:show">
<!-- 				    <div id="character" title = "user_character" onclick = addDynamicDb()>君主表信息</div> -->
				    <a class="staticDb" title="君主表信息" rel="君主表信息" url="dynamic/toDynamic">君主表信息</a>

				</div>
				<div title="GM" style="padding:0px;overflow:show">
					<a class="staticDb" title="本服务器日志" rel="本服务器日志" url="gm/tolog?type=1">本服务器日志</a>
					<a class="staticDb" title="游戏服务器" rel="游戏服务器" url="gm/tolog?type=2">游戏服务器日志</a>
				    <a class="staticDb" title="消息接口测试" rel="消息测试" url="/gm/ts">消息接口测试</a>
				</div>
			</div>
</div>
