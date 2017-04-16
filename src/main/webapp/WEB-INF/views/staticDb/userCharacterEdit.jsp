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
    <div style="margin:10px 0;"></div>
    <div class="easyui-panel" style="width:600">
        <div style="padding:10px 0 10px 60px">
        <form id="ff" method="post">
            <table id="base" title="君主基本信息">
                <tr>
                    <td>君主名称:</td>
                    <td><input class=" type="text" name="name" data-options="required:true" value="${userCharacter.name}" disabled></input></td>
                    <td>账号：</td>
                    <td><input class="" type="text" name="account" data-options="required:true" value="${userCharacter.account_id }" disabled></input></td>
                </tr>
                <tr>
                    <td>等级:</td>
                    <td><input class="" type="text" name="level" data-options="required:true,validType:'email'" value="${userCharacter.level}"  disabled></input></td>
                    <td>VIP等级：</td>
                    <td><input type="text" name="vipId" value="${userCharacter.vip_id}"  disabled/> </td>
                </tr>
                <tr>
                    <td>国家:</td>
                    <td><input class="" type="text" name="country" data-options="required:true" value="${userCharacter.country}"  disabled></input></td>
                    <td>性别：</td>
                    <td><input type="radio" name="gender"  value="1" <%=(request.getAttribute("gender").equals("1")?"checked":"")%> disabled/>男
                        <input type="radio" name="gender" value="0" <%=(request.getAttribute("gender").equals("0")?"checked":"")%> disabled/>女
                    </td>
                </tr>
                <tr>
                    <td>经验值:</td>
                    <td><input type ="text" name="experience" value="${userCharacter.experience}" disabled></intput></td>
                    <td>是否冻结：</td>
                    <td><input type="radio" name="isFreeze" value="0"  <%=(request.getAttribute("isFreeze").equals("0")?"checked":"")%> disabled/>正常
                        <input type="radio" name="isFreeze" value="1" <%=(request.getAttribute("isFreeze").equals("1")?"checked":"")%> disabled/>冻结
                    </td>
                </tr>
                <tr>
                     <td>元宝:</td>
                     <td><input type ="text" name="cash" value="${userCharacter.cash}" disabled></input></td>
                     <td>登陆时间:</td>
                     <td><input type ="text" name="loginTime" value="${userCharacter.login_time}" disabled></input></td>
                </tr>
                <tr>
                    <td>点券:</td>
                    <td><input type="text" name="ticket" value="${userCharacter.ticket}" disabled></input></td>
                    <td>退出时间:</td>
                    <td><input type="text" name="logoutTime" value="${userCharacter.logout_time}" disabled></input></td>
                </tr>
                <tr>
                    <td>君主描述:</td>
                    <td><textarea name=description style="height:60px;" disabled>${userCharacter.description}</textarea></td>
                </tr>
            </table>
            
            
             <table>
              <hr/>
              <tr>
                 <td>联盟:</td>
                 <td><input type="text" name="allianceId" value="${userCharacter.alliance_id}" disabled></input></td>
                 <td>联盟职位:</td>
                 <td><input type="text" name="alliancePosition" value="${userCharacter.alliance_position}" disabled></input></td>
              </tr>
              <tr>
                 <td>武力值:</td>
                 <td><input type="text" name="militaryStrength" value="${userCharacter.military_strength}" disabled> </input></td>
                 <td>潜能点:</td>
                 <td><input type="text" name="abilityPoint" value="${userCharacter.ability_point}" disabled> </input></td>
              </tr>
              <tr>
                  <td>内政值:</td>
                  <td><input type="text" name="internalAffairs" value="${userCharacter.internal_affairs }" disabled></input></td>
                  <td>策略值:</td>
                  <td><input type="text" name="strategyValue" value="${userCharacter.strategy_value}" disabled></input></td>
              </tr>
              <tr>
                 <td>科技点数:</td>
                 <td><input type="text" name="techPoint" value="${userCharacter.tech_point}" disabled></input></td>
                 <td></td>
              </tr>
             </table>
        </form>
        </div>
    </div>
    </div>
</body>
</html>