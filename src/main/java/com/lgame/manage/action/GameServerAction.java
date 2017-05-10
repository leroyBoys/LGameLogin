package com.lgame.manage.action;


import com.lgame.manage.service.FileService;
import com.lgame.manage.service.LoginService;
import com.lgame.model.*;
import com.module.db.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/gameserver")
@Controller
public class GameServerAction {
	@Autowired
	private LoginService loginService;
	@Autowired
	private FileService fileService;
	@RequestMapping(value = "/upload")
	@ResponseBody
	public String upload(@RequestParam MultipartFile file) throws Exception {
		return fileService.upLoad(file);
	}

	@RequestMapping(value={"/register"})
	@ResponseBody
	public Map<String,String> register(REregister re, HttpServletRequest request, HttpSession session){
		Object userInfo = loginService.regedister(re);
		Map<String,String> ret = new HashMap<>();
		if (userInfo instanceof UserInfo) {
			ret.put("suc",String.valueOf(((UserInfo)userInfo).getId()));
			return ret;
		}

		ret.put("error",userInfo.toString());
		return ret;
	}

	@RequestMapping(value={"/version"})
	@ResponseBody
	public SEVersionCheck versionCheck(REVersionCheck vcd, HttpServletRequest request, HttpSession session){
		return loginService.check(vcd);
	}

	/**
	 * 游戏登录处理
	 * @param
	 * @param request
	 * @return
	 */
	@RequestMapping(value={"/login"})
	@ResponseBody
	public Object login(RELogin vcd, HttpServletRequest request, HttpSession session){
		return loginService.login(vcd);
	}

	@RequestMapping(value={"/login_three"})
	@ResponseBody
	public Object login_three(RELoginThird vcd, HttpServletRequest request, HttpSession session){
		return loginService.login_three(vcd);
	}

	@RequestMapping(value={"/modifyPwd"})
	@ResponseBody
	public Map<String,String> modifyPwd(RELoginThird vcd, HttpServletRequest request, HttpSession session){
	//	loginService.changePwd(session,vcd,response);
		Map<String,String> ret = new HashMap<>();
		ret.put("error","");
		return ret;
	}
}
