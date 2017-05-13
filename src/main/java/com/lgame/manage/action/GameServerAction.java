package com.lgame.manage.action;


import com.lgame.manage.service.FileService;
import com.lgame.manage.service.LoginService;
import com.lgame.model.*;
import com.module.db.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
	@RequestMapping(value = "/upload",method = RequestMethod.POST )
	@ResponseBody
	public String upload(@RequestParam MultipartFile file) throws Exception {
		return fileService.upLoad(file);
	}

	@RequestMapping(value={"/register"},method = RequestMethod.POST,consumes = "application/json")
	@ResponseBody
	public Map<String,String> register(@RequestBody REregister re , HttpServletRequest request, HttpSession session){
		Object userInfo = loginService.regedister(re);
		Map<String,String> ret = new HashMap<>();
		if (userInfo instanceof UserInfo) {
			ret.put("suc",String.valueOf(((UserInfo)userInfo).getId()));
			return ret;
		}

		ret.put("error",userInfo.toString());
		return ret;
	}

	@RequestMapping(value={"/version"},method = RequestMethod.POST,consumes = "application/json")
	@ResponseBody
	public SEVersionCheck versionCheck(@RequestBody  REVersionCheck vcd, HttpServletRequest request, HttpSession session){
		return loginService.check(vcd);
	}

	/**
	 * 游戏登录处理
	 * @param
	 * @param request
	 * @return
	 */
	@RequestMapping(value={"/login"},method = RequestMethod.POST ,consumes = "application/json")
	@ResponseBody
	public Object login(@RequestBody RELogin vcd, HttpServletRequest request, HttpSession session){
		return loginService.login(vcd);
	}

	@RequestMapping(value={"/login_three"},method = RequestMethod.POST ,consumes = "application/json")
	@ResponseBody
	public Object login_three(@RequestBody RELoginThird vcd, HttpServletRequest request, HttpSession session){
		return loginService.login_three(vcd);
	}

	@RequestMapping(value={"/modifyPwd"},method = RequestMethod.POST,consumes = "application/json" )
	@ResponseBody
	public Map<String,String> modifyPwd(@RequestBody  REChangePwd vcd, HttpServletRequest request, HttpSession session){
		Object msg = loginService.changePwd(session,vcd);
		Map<String,String> ret = new HashMap<>();
		if(msg instanceof Boolean){
			ret.put("suc",msg.toString());
		}else {
			ret.put("error",msg.toString());
		}
		return ret;
	}
}
