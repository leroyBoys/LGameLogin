package com.lgame.manage.action;


import com.lgame.manage.service.FileService;
import com.lgame.manage.service.LoginService;
import com.lgame.model.*;
import com.lgame.util.comm.StringTool;
import com.lgame.util.json.JsonUtil;
import com.module.db.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

	@RequestMapping(value={"/register"},method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> register(String data , HttpServletRequest request, HttpSession session){
		REregister re = (REregister) JsonUtil.getBeanFromJson(data,REregister.class);
		Object userInfo = loginService.regedister(re);
		Map<String,String> ret = new HashMap<>();
		if (userInfo instanceof UserInfo) {
			ret.put("suc",String.valueOf(((UserInfo)userInfo).getId()));
			return ret;
		}

		ret.put("error",userInfo.toString());
		return ret;
	}

	@RequestMapping(value={"/version"},method = RequestMethod.POST)
	@ResponseBody
	public SEVersionCheck versionCheck( String data , HttpServletRequest request, HttpSession session){
		return loginService.check((REVersionCheck) JsonUtil.getBeanFromJson(data,REVersionCheck.class));
	}

	/**
	 * 游戏登录处理
	 * @param
	 * @param request
	 * @return
	 */
	@RequestMapping(value={"/login"},method = RequestMethod.POST )
	@ResponseBody
	public Object login(String data, HttpServletRequest request, HttpSession session){
		return loginService.login((RELogin) JsonUtil.getBeanFromJson(data,RELogin.class));
	}

	@RequestMapping(value={"/login_three"},method = RequestMethod.POST )
	@ResponseBody
	public Object login_three(String data, HttpServletRequest request, HttpSession session){
		return loginService.login_three((RELoginThird) JsonUtil.getBeanFromJson(data,RELoginThird.class));
	}

	@RequestMapping(value={"/modifyPwd"},method = RequestMethod.POST )
	@ResponseBody
	public Map<String,String> modifyPwd(String data, HttpServletRequest request, HttpSession session){
		Object msg = loginService.changePwd(session,(REChangePwd) JsonUtil.getBeanFromJson(data,REChangePwd.class));
		Map<String,String> ret = new HashMap<>();
		if(msg instanceof Boolean){
			ret.put("suc",msg.toString());
		}else {
			ret.put("error",msg.toString());
		}
		return ret;
	}

}
