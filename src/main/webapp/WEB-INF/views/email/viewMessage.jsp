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
<body>
<div style="padding:10px;" id="content">
   <div class="easyui-panel"  style="width:600">
		<div style="padding:10px 0 10px 60px;">
	    <form  method="post" style="content" action="equipment/update">
	    	<table>
	    		<tr>
	    			<td>邮件标题:</td>
	    			<td><input type="text" name="email_title"   value="${email.title}" disabled></input></td>
	    		</tr>
	    		<tr>
	    			<td>邮件状态:</td>
	    			<td><input type="text" name="email_title"   value="${email.status == 0?'未读':email.status == 1?'已读':'已提取'}" disabled></input></td>
	    		</tr>
	    		<tr>
	    		   <td>发件人ID:</td>
	    		   <td><input type="text" name="email_addresser" value="${email.addresser}" disabled></input></td>
	    		</tr>
	    		<tr>
	    			<td>收件人ID:</td>
	    			<td><input type="text" name="email_addressee"  value="${email.addressee}" disabled></input></td>
	    		</tr>
	    		<tr>
	    			<td>金锭数量:</td>
	    			<td><input type="text" name="email_cash" value="${email.cash}" disabled/></td>
	    		</tr>
	    		<tr>
	    			<td>邮件内容:</td>
	    			<td><textarea rows="2" cols="15"   name="email_content"  disabled>${email.content}</textarea>   </td>
	    		</tr>
	    		<tr>
	    			<td>附件及金锭:</td>
	    			<td>
	    				<c:choose>
	    					<c:when test="${empty email.attachment  }">
	    						无附件
	    					</c:when>
	    					<c:otherwise>
		    					  <select multiple="multiple" size="${fn:length(email.attachment) }" disabled> 
			                           <c:forEach items="${email.attachment}" var="map"> 
			                                 <option >${map['item_name']}(${map['item_no']})*${map['item_num']}${map['item_amount']}</option> 
			                           </c:forEach> 
			                     </select>  
	    					</c:otherwise>
	    				</c:choose>
	    			 </td>
	    		</tr>
	    	</table>
	    </form>
	    </div>
	</div>
    </div>
</body>
</html>