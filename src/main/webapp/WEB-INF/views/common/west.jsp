<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div data-options="region:'west',split:true" title="管理菜单" style="width:150px;padding:0px;">
			<div id="menu" class="easyui-accordion" data-options="fit:true,border:false">
				<div title="游戏数据管理" id="staticDb" style="padding:0px;overflow: show">
				</div>
				<div title="用户信息管理"  id="dynamicDb" style="padding:0px;overflow:show">
<!-- 				    <div id="character" title = "user_character" onclick = addDynamicDb()>君主表信息</div> -->
				    <a class="staticDb" title="君主表信息" rel="君主表信息" url="dynamic/toDynamic">君主表信息</a>
				    <a class="staticDb" title="玩家装备信息" rel="玩家装备信息" url ="/equipment">玩家装备信息</a>
				    <a class="staticDb" title="邮件系统" rel="邮件系统" url ="/email/toEmails">邮件系统</a>
				    <a class="staticDb" title="添加货币" rel="添加货币" url ="/email/addMoney">添加货币</a>
				    <a class="staticDb" title="添加装备" rel="添加装备" url ="/email/addEquipment">添加装备</a>
				    <a class="staticDb" title="添加消耗道具" rel="添加消耗道具" url ="/email/addOrdinaryItem">添加消耗道具</a>
				</div>
				<div title="游戏客服管理" style="padding:0px;overflow:show">
				    <a class="staticDb" title="货币资源日志" rel="货币资源日志" url="log/toVirtualLog">货币资源日志</a>
				    <a class="staticDb" title="物品道具日志" rel="物品道具日志" url="log/toItemLog">物品道具日志信息</a>
				</div>
			</div>
</div>
