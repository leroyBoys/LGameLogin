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
  	<div class="easyui-panel" >
		<div style="padding:10px 0 10px 60px;">
	    <form  method="get"  action="equipment/update">
   			<input type="hidden" name="equipment_id" value="${equipment.id}"/>
   			<input type="hidden" name="equipment_no" value="${equipment.item_no}"/>
	    	<table id="equipmentUpdate" >
	    		<tr>
	    			<td>道具名称:</td>
	    			<td>${equipment.item_name }</td>
	    		</tr>
	    		<tr>
	    			<td>操作类型:</td>
	    			<td>
	    			<select  onchange="change(this)" name="equipment_type">
	    				<option value="0">修改基本信息</option>
	    				<option value="1">更改所属君主</option>
	    			</select>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>道具所属君主id:</td>
	    			<td><input type="text" value="${equipment.character_id }"  name="equipment_character_id"  disabled="disabled"/></td>
	    		</tr>
	    		<tr>
	    			<td>状态:</td>
	    			<td>
	    			<select name="equipment_is_bound" id="equipment_is_bound">
	    				<option value="0" ${equipment.is_bound==0?'selected=selected':''}>未绑定</option>
	    				<option value="1"  ${equipment.is_bound==1?'selected=selected':''}>绑定</option>
	    			</select>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>强化等级:</td>
	    			<td><input type="text" name="equipment_strength_level" class="numbox" id="strength_level"  value="${equipment.strength_level }" onchange="equipmentStrength(this,'${equipment.item_no }')"/> </td>
	    		</tr>
	    		<tr>
	    			<td>孔1:</td>
	    			<td><input type="text" name="equipment_hole1" value="${equipment.hole1 }"/> </td>
	    		</tr>
	    		<tr>
	    			<td>孔2:</td>
	    			<td><input type="text" name="equipment_hole2" value="${equipment.hole2 }"/> </td>
	    		</tr>
	    		<tr>
	    			<td>孔3:</td>
	    			<td><input type="text" name="equipment_hole3" value="${equipment.hole3 }"/> </td>
	    		</tr>
	    		<tr>
	    			<td>武力强化值:</td>
	    			<td><input type="text" name="equipment_strengthen_force" disabled="disabled" class="numbox" value="${equipment.strengthen_force }"/> </td>
	    		</tr>
	    		<tr>
	    			<td>强化附件某略值:</td>
	    			<td><input type="text" name="equipment_strengthen_strategy" disabled="disabled" class="numbox" value="${equipment.strengthen_strategy }"/> </td>
	    		</tr>
	    		<tr>
	    			<td>强化附件体质值:</td>
	    			<td><input type="text" name="equipment_strengthen_physique" disabled="disabled" class="numbox" value="${equipment.strengthen_physique }"/> </td>
	    		</tr>
	    		<tr>
	    			<td>强化附件身法值:</td>
	    			<td><input type="text" name="equipment_strengthen_agility" disabled="disabled" class="numbox" value="${equipment.strengthen_agility }"/> </td>
	    		</tr>
	    	</table>
	    </form>
	    </div>
	    <div style="text-align:center;padding:5px">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" id= "submitFormStrength" onclick="submitForm(this,'equipment')">提交</a>
	    	<script type="text/javascript" >
		    	/**
		    	 * 提交并关闭当前窗口
		    	 */
		    	function submitForm(_,url){
		    		var form = $(_).parent().parent().find("form");
		    		var strength = $("#equipmentUpdate").find("input[name^='equipment_strengthen_']");
		    		for(var i = 0;i<strength.length;i++){
		    			strength.eq(i).removeAttr("disabled");
		    		}
		    		form.form("submit",{
		    			success:function(data){
		    				data = eval('(' + data + ')');
		    				if(data.status == 'succ'){
		    					$("#temp").window("close");
		    					alert("保存成功,请刷新页面获得最新数据");
		    				}else{
		    					alert(data.error);
		    				}
		    			}
		    		});
		    	}
	    		 function change(_){
	    			var i =  $(_).val();
	    			if(i == 0){
	    				var inputs = $("#equipmentUpdate").find("input");
	    				for(var i = 0 ;i < inputs.length;i++){
	    					inputs.eq(i).parent().parent().show();
	    				}
		    			$("#equipment_is_bound").parent().parent().show();
		    			$("#equipmentUpdate").find("input[name='equipment_character_id']").attr("disabled","disabled");
	    			}else{
	    				var inputs = $("#equipmentUpdate").find("input");
	    				for(var i = 0 ;i < inputs.length;i++){
	    					inputs.eq(i).parent().parent().hide();
	    				}
		    			$("#equipment_is_bound").parent().parent().hide();
		    			$("#equipmentUpdate").find("input[name='equipment_character_id']").parent().parent().show();
		    			$("#equipmentUpdate").find("input[name='equipment_character_id']").removeAttr("disabled");
	    			}
	    		}
	    		function equipmentStrength(_,equipmentNo){
	    			if(!dataValidation.isNum($(_).val()) ||  $(_).val() <= 0){
	    				$("#strength_level").val(0);
	    				 $("#equipmentUpdate").find("input[name='equipment_strengthen_force']").val(0);
    					 $("#equipmentUpdate").find("input[name='equipment_strengthen_strategy']").val(0);
    					 $("#equipmentUpdate").find("input[name='equipment_strengthen_physique']").val(0);
    					 $("#equipmentUpdate").find("input[name='equipment_strengthen_agility']").val(0);
	    				return;
	    			}
	    			$("#submitFormStrength").linkbutton("disable");
	    			$.getJSON(formatUrl("db/getEquipmentStrength?p="+new Date()),{level:$(_).val(),equipmentNo:equipmentNo},function(data){
	    				if(jQuery.isEmptyObject(data)){
	    					alert("强化等级超出系统设置值,请重新设置");
	    					$(_).focus();
	    					$("#strength_level").val(0);
	    					 $("#equipmentUpdate").find("input[name='equipment_strengthen_force']").val(0);
	    					 $("#equipmentUpdate").find("input[name='equipment_strengthen_strategy']").val(0);
	    					 $("#equipmentUpdate").find("input[name='equipment_strengthen_physique']").val(0);
	    					 $("#equipmentUpdate").find("input[name='equipment_strengthen_agility']").val(0);
	    					return;
	    				}
	    				if(data.addForce != null){
	    					 $("#equipmentUpdate").find("input[name='equipment_strengthen_force']").val(data.addForce);
	    				}
	    				if(data.addStrategy != null){
	    					 $("#equipmentUpdate").find("input[name='equipment_strengthen_strategy']").val(data.addStrategy);
	    				}
	    				if(data.addPhysique != null){
	    					 $("#equipmentUpdate").find("input[name='equipment_strengthen_physique']").val(data.addPhysique);
	    				}
	    				if(data.addAgility != null){
	    					 $("#equipmentUpdate").find("input[name='equipment_strengthen_agility']").val(data.addAgility);
	    				}
	    				$("#submitFormStrength").linkbutton("enable");
	    			});
	    		}
	    	</script>
	    </div>
	</div>
</div>
</body>
</html>