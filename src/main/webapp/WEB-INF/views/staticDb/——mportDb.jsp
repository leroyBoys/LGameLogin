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
<script type="text/javascript" src="${basePath }/static/tool/easyUI/jquery.datagrid.js"></script>
<script type="text/javascript" src="${basePath }/static/js/main.js"></script>
</head>
<body >
	<c:set var="random" value="${random == null ? 0:random+1}" scope="session"></c:set>
	<div id="tb${random}" style="height:auto;width:auto">
			<a href="javascript:void(0)" class="easyui-linkbutton" id="verification${random }"  data-options="iconCls:'icon-search',plain:true" onclick ="verificationCvs('#dg${random}','${verifyData }')">数据校验</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="appendCsv('#dg${random}')">添加</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"  data-options="iconCls:'icon-remove',plain:true" onclick ="removeCsv('#dg${random}')">删除</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" onclick="$('#dg${random}').datagrid('acceptChanges')">刷新</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"  data-options="iconCls:'icon-save',plain:true" url="${clearUrl }" id="importUrl" saveUrl = "${saveUrl}" onclick ="importDb.importDbNow('#dg${random}','${verifyData }')">确定导入</a>
		<%-- <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="getChanges()">GetChanges</a>
	--%>
	</div>
	
	<table id="dg${random}" class="easyui-datagrid" title="" style="width:auto"
			data-options="
				iconCls: 'icon-edit',
				fit:true,
				loader:myLoader,
				toolbar:'#tb${random }',
				onClickRow: onClickRow,
				url:'${basePath }/${readCsv}'
			">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<c:forEach items="${commentList }" var="comm">
					<c:if test="${comm.name !='id' }">
						<th data-options="field:'${comm.name}',editor:'${comm.dataType }',styler:cellStyler"  >
							<c:choose>
								<c:when test="${comm.comment != ''}">
									<b><a  title="字段中文名(字段英文名[字段数据类型])">${comm.comment}(${comm.name }[${comm.dataType }])</a></b>
								</c:when>
								<c:otherwise>
									<b><a  title="字段英文名[字段数据类型]">${comm.name}[${comm.dataType }]</a></b>
								</c:otherwise>	
							</c:choose>
						</th>
					
					</c:if>
				</c:forEach>
				<th data-options="field:':dataError',editor:'text',styler:cellStyler"  >异常数据</th>
			</tr>
		</thead>
	</table>
	<script type="text/javascript" >
	function appendCsv(dg){//增加table
		dgId = dg;
		if (endEditing()){
			$(dgId).datagrid('appendRow',{});
			editIndexMap.put(dgId,$(dgId).datagrid('getRows').length-1);
			$(dgId).datagrid('selectRow', editIndexMap.get(dgId))
					.datagrid('beginEdit',  editIndexMap.get(dgId));
		}
	}
	function removeCsv(dg){//增加table
		dgId = dg;
		var dgTable = $(dgId);
		var checked = dgTable.datagrid('getChecked');
		dgTable.datagrid('cancelEdit', editIndexMap.get(dgId));
		if(checked.length > 0){
			for(var i=0;i<checked.length;i++){
				var index = dgTable.datagrid('getRowIndex',checked[i]);
				dgTable.datagrid('deleteRow', index);
			}
			editIndexMap.put(dgId,undefined);
			dgTable.datagrid('acceptChanges');
			//appendTip("成功删除"+checked.length+"条数据");
		}else{
			appendTip("没有选择,无法删除");
		}
	}
	function verificationCvs(dg,verifyDataURL){
			
		dgId = dg;
		/* if($(dgId).datagrid('getRows').length != $(dgId).datagrid('getChecked').length){
			appendTip("请将不需要校验的数据删除以后再校验");
			return;
		} */
		if(verificationCvsIndex > 0){
			verificationCvsIndex = -1;
			 clearTimeout(start);
			 $(dgId.replace("dg","verification")).linkbutton({
			    	text: '数据校验'
		    });
			 return;
		}
	    $(dgId.replace("dg","verification")).linkbutton({
	    	text: '停止数据校验'
	    });
		progressbar();
		verificationCvsIndex = 0;
		start();
		var rows = $(dgId).datagrid('getRows');
		var json = $.toJSON(rows);
		json = encodeURI(json);
		$.ajax({
		   type: "POST",
		   url: formatUrl(verifyDataURL),
		   data: json,
		   success: function(data){
			   if(data.status == 'succ'){
				   importDb.error = data.errorLine;
				   importDb.msg = data.errorMsg;
			   }else{
				   var data = eval('(' + data + ')');
				   clearTimeout(start);
				   verificationCvsIndex = -1;
				   appendTip(data.error);
				   $(dgId.replace("dg","verification")).linkbutton({
				    	text: '数据校验'
			    });
			   }
		   }
		});
	}
	
	var verificationCvsIndex = 0;
	var msgTip = "";
	function start(){
		var rows = $(dgId).datagrid('getRows');
		if(verificationCvsIndex == -1){
			$(dgId).datagrid('unselectAll');
			$('#progressbar').remove();
		}else if(verificationCvsIndex < rows.length){
			$(dgId).datagrid('selectRow', verificationCvsIndex);
			if(isNeedVerification()){
				$(dgId).datagrid('beginEdit', verificationCvsIndex).datagrid('endEdit', verificationCvsIndex); 
			}
			$('#progressbar').progressbar('setValue', ((verificationCvsIndex+1)*100/rows.length).toPrecision(4));
			verificationCvsIndex++;
			setTimeout(start, 1);
		}else{
			$('#progressbar').remove();
			if(msgTip.length == 0){
				if(importDb.error.length > 0){
					msgTip = "发现"+importDb.error.length+"条数据错误,"+importDb.msg;
					appendError(msgTip);
				}else{
					$(dgId).datagrid('unselectAll');
					$(dgId).datagrid('hideColumn', ":dataError");
					msgTip = ",没有发现错误,可以执行导入操作";
					appendTip("成功完成"+rows.length+"条数据校验"+msgTip);
				}
			}else{
				appendError(msgTip);
			}
			msgTip = "";
			verificationCvsIndex = 0;
			importDb.error = -1;
			$(dgId.replace("dg","verification")).linkbutton({
			    	text: '数据校验'
		    });
		}
	};
	/***是否需要页面验证**/
	function isNeedVerification(){
		if(importDb.error == -1){
			return true;
		}else if(importDb.error.length == 0){
			return false;
		}else if(importDb.error[importDb.error.length-1] < verificationCvsIndex+1){
			return false;
		}
		if(importDb.error.length > 0){
			msgTip=importDb.msg;
			verificationCvsIndex = $(dgId).datagrid('getRows').length;
			return true;
		}
		/* for(var i = 0;i<importDb.error.length;i++){
			if(importDb.error[i] == verificationCvsIndex){
				if(i > 10){
					msgTip=":数据异常严重，请检查数据结构是否和数据库一致";
					verificationCvsIndex = $(dgId).datagrid('getRows').length;
				}
				return true;
			}
		} */
		return false;
	}

	var importDb = {
			allRow:0,
			failNum:0,
			error:-1,
			msg:''
	};
	importDb.importDbNow = function(dg,verifyDataURL){
		dgId = dg;
		$(dgId).datagrid('acceptChanges');
		$.messager.confirm('', "重新导入将自动删除该库中所有数据,确认是否继续改操作", function(r){
			if(!r){
				return;
			}
			var rows = $(dgId).datagrid('getChecked');
			if(rows.length <= 0){
				appendTip("请选择要导入的数据");
				return;
			}
			$.messager.progress({
				title:'',
				timeout:0,
				msg:'正在导入中，请稍后',
				text:''
			});
			if($("#importUrl").linkbutton("options").disabled){
				return
			}
			$("#importUrl").linkbutton("disable");
			//数据情况成功则导入数据
			var url = $("#importUrl").attr("saveUrl");
			var rows = $(dgId).datagrid('getChecked');
			var json = $.toJSON(rows);
			json = encodeURI(json);
			$.ajax({
			   type: "POST",
			   url: formatUrl(url),
			   data: json,
			   success: function(data){
				   if(data.status == 'succ'){
					   appendTip("成功导入"+rows.length+"条数据");
				   }else{
					    var data = eval('(' + data + ')');
						appendTip("数据导入失败："+data.error);
				   }
					$.messager.progress('close');
					$("#importUrl").linkbutton("enable");
			   }
			});
		});
	};

	</script>
</body>
</html>
