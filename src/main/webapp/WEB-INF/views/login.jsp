<%@page import="com.lgame.utils.PropertiesUtils"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<c:set var="basePath" value="${pageContext.request.scheme}://${pageContext.request.serverName }:${pageContext.request.serverPort}${path }"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>北京飞羽游戏管理系统登陆</TITLE></HEAD>
<link rel="stylesheet" type="text/css" href="${basePath}/static/css/User_Login.css"/>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<style type="text/css">
	/** basic **/
	body{ background: #fff url(http://p9.qhimg.com/d/inn/f4810fcc/xzmj/xzmjbg.jpg); font: 400 12px/19px "\5FAE\8F6F\96C5\9ED1","\5B8B\4F53"; }
	img{ display: inline-block; vertical-align: middle; }
	.db{ display: block; }
	.dib{ display: inline-block; }
	.tal{ text-align: left; }
	.vm{ vertical-align: middle; }
	.dn{ display: none!important; }
	.vmalign{ display: inline-block; width: 0; height: 100%; vertical-align: middle; overflow: hidden; }
	.tac{ text-align: center; }
	.red{ color: #ff0000; }
	.l{ float: left; _display: inline; }
	.r{ float: right; _display: inline; }

	/* banner */
	.mjBanWarp,
	.mjBanBox{ height: 630px; background: url(http://p0.qhimg.com/d/inn/d57f4e8a/mjBanBg06.jpg) center top no-repeat; }
	.mjBanBox,
	.mjInfo,
	.mjHelp,
	.mjAct{ width: 1000px; margin: 0 auto; }
	.mjBanBox{ overflow: hidden; }
	.mjBanNav a{ vertical-align: top; font-size: 17px; color: #2c2c2c; }


	.mjStartGame{ width: 280px; margin: 324px 0 0 111px; _margin-top: 294px; position: absolute; }
	.mjBanNav{ margin-top: 48px; padding-left: 300px; }
	.mjBanNav a{ display: inline-block; width: 78px; height: 33px; text-align: center; line-height: 29px; }
	.mjBanNav a:hover,
	.btnStartGame a,
	.btnStartGame{ width: 280px; height: 104px; }
	.btnStartGame{  background: url(http://p2.qhimg.com/d/inn/f4810fcc/xzmj/mjStartgame02.png) no-repeat; _filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=noscale, src="http://p4.qhimg.com/d/inn/9c5a7b7f/imgs/mjStartgame.png"); _background: none; }
	.btnStartGameDisabled{ background: url(http://p9.qhimg.com/d/inn/b8b13c95/mjStartgameDisabled.png) no-repeat; _filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=noscale, src="http://p9.qhimg.com/d/inn/b8b13c95/mjStartgameDisabled.png"); _background: none; }
	.btnStartGame a{ display: block; _position: relative; }
	.mjStartInfo{ padding: 0 22px; }
	.mjStartInfo a,
	.mjStartInfo span{ font-size: 20px; color: #094509; line-height: 25px; }
	.mjInfo{ padding: 60px 0 34px; color: #3d3d3d; overflow: hidden; }

	/* help */
	.mjHelp{ padding-top: 20px; }
	.mjHelpNav a span,
	.mjHelpBack,
	.mjHelpNav i.navFir,
	.mjHelpNav a span,
	.mjHelpNav i.navFir,
	.mjHelpNav i.navFir span{ margin-bottom: 4px; display: block; width: 230px; height: 39px; }
	.mjHelpNav a.current,
	.mjHelpNav a:hover{ font-size: 19px; _margin-bottom: 0; background-position: 0 -63px; _background-position: 0 -53px; text-decoration: none; }
	.mjHelpList{ margin-top: -18px; float: right; width: 720px; }
	.mjHelpBack{ background-position: -4px -373px; line-height: 36px; font-family: \5B8B\4F53; color: #265a58; }
	.mjHelpBack a{ color: #265a58; }
	.mjHelpItem{ display: none; font-size: 14px; color: #154d4c; line-height: 24px; padding-bottom: 120px; min-height: 727px; _height: 727px; }
	.mjHelpItem p{ text-indent: 2em; }
	.mjHelpItem h2{ text-align: center; margin-top: 8px; font-size: 23px; color: #154d4c; line-height: 55px; }
	.numIcon{ display: inline-block; padding: 0 3px; line-height: 16px; background: #154d4b; color: #fff; }
	.textIndent2{ padding-left: 40px; }
	.textIndent2 p{ text-indent: 0; }

	/* activity */
	.mjActRedBg,
	.mjSNav,
	.mjSNav a{ font-family: \5B8B\4F53; color: #fff; }
	.mjActIcon,
	.mjActRedBg{ background-position: -3px -2px; }
	.mjActGreenBg{ background-position: -3px -54px; }
	.mjActOne{ background-position: -14px -9px; }
	.mjActTwo{ background-position: -244px -14px; }
	.mjActThree{ background-position: -506px -14px; }
	.mjActFour{ background-position: -748px -14px; }
	.mjActFive{ background-position: -9px -113px; }
	.mjActTitle,
	.mjActTitleh3{ font-size: 18px; color: #c13737; line-height: 42px; text-align: center; margin-top: -26px; margin-bottom: 36px; white-space: nowrap; }
	.mjActTitleh3{ display: none; }
	.mjActText{ height: auto; padding: 0 40px 0 29px; line-height: 21px; font-size: 14px; color: #393939; overflow: hidden; }
	.mjActTable{ margin: 8px 0 0 29px; }
	.mjActTable table{ background: #fafaef;  }
	.mjActTable table td{ height: 27px; border: 1px solid #c9c9c9; padding-left: 36px; color: #6c6c6c; font-size: 14px; }
	.mjActTable table tr th{ height: 26px;  font-size: 14px; color: #e7ecec; }
	.mjActTabTit{ height: 26px!important; color:#e7ecec!important; width: 278px; }
	.mjActOpen{ margin-top: 26px; text-align: right; padding-right: 40px; display: none; }
	.mjActOpen a{ display: inline-block; cursor: pointer; height: 16px; line-height: 16px; padding: 0 10px; background: #165730; color: #fbf8dc; }
	.mjActOpen a:hover{ text-decoration: none; }
	.mjActList{ padding-bottom: 94px; }
	.mjActItem.current{ margin-bottom: 27px; }
	.mjActItem.current .mjActOpen{ display: none; }
	.mjActItem.current .mjActTable{ display: none; }
	.mjActItem.current .mjActText{ height: 40px; }
	.mjActBox .mjBanNav{ margin-top: 45px; padding-left: 277px; }
	.mjInfo .mjActPic { display: block; }
	.curMenu{ color: #ffeb62; }


	/* index reset */
	.mjInfo .mjActIcon,
	.mjInfo .mjActItem,
	.login,
	.unlogin,
	.mjInfo .mjActTable,
	.mjInfo .mjActTitle{ display: none; }
	.mjInfo .mjActItem{ float: left; width: 165px; height: 160px; padding: 0 3px 0 165px; _overflow: hidden; background-image: none; }
	.mjInfo .mjActOpen{ margin-top: 0; text-align: left; display: block; }
	.mjInfo .mjActOpen a{ padding: 0 5px; color: #fbf8dc; }
	.mjInfo .mjActText{ padding: 0; color: #3d3d3d; height: 42px; }
	.mjInfo .mjInfoFir{ background-position: 0 0; }
	.mjInfo .mjInfoSec{ background-position: 0 -197px; }
	.mjInfo .mjInfoThi{ background-position: 0 -389px; }
	.mjInfo .mjActList{ padding-bottom: 0; width: 1200px; }
	.mjActPic { position: absolute; left: 0; top: 0; width: 165px; height: 156px; display: none; }
	.mjInfo .mjActPic,
	.mjInfo .mjActTitleh3{ display: block; }
	.mjInfo p{ font-size: 14px; line-height: 20px; }
	.mjInfo a{ margin-top: 5px; font-size: 13px; color: #139fe8; display: inline-block; }
	.mjInfo .mjActTitle,
	.mjInfo .mjActTitleh3{ font-size: 24px; line-height: 36px; margin-top: 7px; text-align: left; color: #3d3d3d; margin-bottom: 0; }
	.mjInfo .mjActTitleh3{ overflow: hidden; text-overflow: ellipsis; width: 165px; -o-text-overflow: ellipsis; }

	/* scroll banner*/
	.mjScrollWrap,
	.mjScroll,
	.focus,
	.focus .scrollist,
	.focus .scrollist .scrollItem{ min-height: 305px; _height: 305px; }
	.mjScrollWrap{ background: #edebe4;}
	.mjScroll{width: 970px; position: relative; margin: 0 auto;}
	.focus{width: 673px; margin-top: 5px; margin-left: 148px; float: left; overflow: hidden; *position:relative; _margin-left: 50px;}
	.focus .scrollist{ position: relative; left: 0;}
	.focus .scrollist .scrollItem{width: 674px; float: left; }
	.focus .btns{height: 0;}
	.focus .btnsInner{ height: 20px; line-height: 20px; z-index: 10; position: absolute; left: 50%; bottom: 0; }
	.focus .btnsInner .circleItem{ position: relative; right: 50%; padding: 0 4px; margin-right: 3px; cursor: pointer; float: left; text-align: center; font-weight: 700; font-size: 15px; }
	.focus .btnsInner .circleCur{ font-size: 20px; color: #50CF11; text-decoration: underline; }
	.btnLeft,
	.btnLeftDisable{ left: 0; }
	.btnRight,
	.btnRightDisable{ right: 0; }
	.btnLeft{ background-position: -40px 0; _margin-left: 30px; _background-position: -40px 0;}
	.btnRight{ background-position: -40px -96px; _margin-left: 50px; _background-position: -40px -96px;}
	.btnLeftDisable{ cursor: default; background-position: 0 0; _margin-left: 30px; _background-position: 0 0;}
	.btnRightDisable{ cursor: default; background-position: 0 -96px; _margin-left: 50px; _background-position: 0 -96px;}

	.scrollItemInner{position: relative; z-index: 20; margin-top: 25px;}
	.paraHead{float: left;}
	.paraContent{width: 585px; float: left;}
	.scrollItem h3{display: inline; color: #165730; font-size: 26px; font-family: arial, 'Hiragino Sans GB', 'Microsoft Yahei', '微软雅黑', '宋体', \5b8b\4f53, Tahoma, Arial, Helvetica, STHeiti; line-height: 70px; vertical-align: middle; padding-right: 20px;}
	.scrollItem a{background: #165730; color: #fbf8dc; font-size: 12px; text-decoration: none; font-family: arial, 'Hiragino Sans GB', 'Microsoft Yahei', '微软雅黑', '宋体', \5b8b\4f53, Tahoma, Arial, Helvetica, STHeiti; cursor: pointer; padding-left: 4px; padding-right: 5px; vertical-align: middle; padding: 2px 10px; }
	.scrollItem p{color: #000000; font-size: 17px; font-family: arial, 'Hiragino Sans GB', 'Microsoft Yahei', '微软雅黑', '宋体', \5b8b\4f53, Tahoma, Arial, Helvetica, STHeiti; line-height: 27px;}
</style>
<body>
<div>
	<!-- Banner -->
	<div class="mjBanWarp">
		<div class="mjBanBox">
			<!-- nav -->
		<%--	<div class="mjBanNav">
				<a class="mjHome current" href="index.html" title="首页">首页</a>
				<span class="menuLine"></span>
				<!-- a class="mjAct" href="activity.html" title="活动">活动</a>
                <span class="menuLine"></span -->
				<a class="mjHelpn" href="help.html" title="帮助">帮助</a>
			</div>--%>
			<!-- start game -->
			<div class="mjStartGame">
				<div class="btnStartGame">
					<a class="btnEnter js_openPEgame"  data-url="http://xzmj.qipai.360.cn/" data-appid= "0" title="立刻加入" href="http://down.360safe.com/360game/360xzmj_setup1017.exe"
					></a>
				</div>
			</div>
			<div class="toBottom"></div>
		</div>
	</div>
</body>
</html>
