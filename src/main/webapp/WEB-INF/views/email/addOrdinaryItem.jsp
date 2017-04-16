<%@page import="com.crystalcg.gamedev.utils.enums.ItemType"%>
<%@page import="com.crystalcg.gamedev.utils.enums.MailItemType"%>
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
<script type="text/javascript" src="${basePath }/static/js/addMoney.js"></script>
</head>
<body >
<div style="padding:10px;" id="content">
  	<div class="easyui-panel"  style="width:auto:">
		<div style="padding:10px 0 10px 60px;">
	    <form  method="post" style="margin-left:400px" action="email/addMoney/save">
	    	<table>
	    		<tr>
	    			<td>邮件标题:</td>
	    			<td><input type="text"  class="easyui-validatebox" data-options="required:true" name="title"/></td>
	    		</tr>
	    		<tr>
	    			<td><b style="font-style: italic;">发件人ID:</b></td>
	    			<td><input type="text" class="easyui-numberbox" name="email_addresser" ></input><span style="margin-left:10px;font-size:14px;color:#CC6600">默认:系统</span></td>
	    		</tr>
	    		<tr>
	    			<td>收件人ID:</td>
	    			<td><input type="text" class="easyui-validatebox easyui-numberbox" data-options="required:true" name="addressee" /></td>
	    		</tr>
	    		<tr>
	    			<td>发送物品道具编号/数量:</td>
	    			<td>
	    				<input type="text" class="itemNo" />
	    				<input type="text" class="itemCount easyui-numberbox"  value="1"/>
	    				<a href="javascript:void(0)" rel="email/getOrdinaryItem" openURL="db/toSearchItem/<%=MailItemType.item.getValue() %>/ordinary" onclick="addMoneyItem(this)">添加</a>
	    				<div class="win"></div>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>发送材料道具编号/数量:</td>
	    			<td>
	    				<input type="text" class="itemNo" />
	    				<input type="text" class="itemCount easyui-numberbox"  value="1"/>
	    				<a href="javascript:void(0)" rel="email/getMaterialItem" openURL="db/toSearchItem/<%=MailItemType.material.getValue() %>/0" onclick="addMoneyItem(this)">添加</a>
	    				<div class="win"></div>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>邮件内容:</td>
	    			<td><textarea rows="2" cols="15"    name="content"></textarea>   </td>
	    		</tr>
	    		<tr>
	    			<td>附件:</td>
	    			<td>
	    			<select multiple="multiple" class="attachs" ondblclick='removeSelect(this)' size="1"  name="email_attach">
	    				<option value="0">无</option>
	    			</select>
	    			<span style="margin-left:10px;font-size:14px;color:#CC6600">提示:双击选项删除</span>
	    			 </td>
	    		</tr>
	    	</table>
	    </form>
	    </div>
	    <div style="text-align:center;padding:5px">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" reloadUrl="/email/addOrdinaryItem" onclick="moneySubmitForm(this)">提交</a>
	    </div>
	</div>
</div>
</body>
</html>