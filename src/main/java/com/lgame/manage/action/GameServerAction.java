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
	public Map<String,Object> register(String data , HttpServletRequest request, HttpSession session){
		REregister re = (REregister) JsonUtil.getBeanFromJson(data,REregister.class);
		Object userInfo = loginService.regedister(re);
		if (userInfo instanceof UserInfo) {
			return getReturnMapData(null,String.valueOf(((UserInfo)userInfo).getId()));
		}

		return getReturnMapData(userInfo.toString(),null);
	}

	private Map<String,Object> getReturnMapData(String error,Object data){
		Map<String,Object> ret = new HashMap<>();

		if(error != null){
			ret.put("error",error);
		}else {
			ret.put("suc",data);
		}
		return ret;
	}

	@RequestMapping(value={"/version"},method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> versionCheck( String data , HttpServletRequest request, HttpSession session){
		REVersionCheck checkdata = (REVersionCheck) JsonUtil.getBeanFromJson(data,REVersionCheck.class);

		SEVersionCheck check = loginService.check(checkdata);
		if(check == null){
			return this.getReturnMapData("cant find data from srcId:"+checkdata.getSrcId()+"  gameId"+checkdata.getGameId(),null);
		}
		return this.getReturnMapData(null,check);
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

		Object ret = loginService.login((RELogin) JsonUtil.getBeanFromJson(data,RELogin.class));
		if(ret != null && ret instanceof SELogin){
			return this.getReturnMapData(null,ret);
		}
		return this.getReturnMapData(ret==null?"null":ret.toString(),null);
	}

	@RequestMapping(value={"/login_three"},method = RequestMethod.POST )
	@ResponseBody
	public Object login_three(String data, HttpServletRequest request, HttpSession session){

		Object ret = loginService.login_three((RELoginThird) JsonUtil.getBeanFromJson(data,RELoginThird.class));
		if(ret == null || ret instanceof String){
			return this.getReturnMapData(ret==null?"null":ret.toString(),null);
		}

		return this.getReturnMapData(null,ret);
	}

	@RequestMapping(value={"/modifyPwd"},method = RequestMethod.POST )
	@ResponseBody
	public Map<String,Object> modifyPwd(String data, HttpServletRequest request, HttpSession session){
		Object msg = loginService.changePwd(session,(REChangePwd) JsonUtil.getBeanFromJson(data,REChangePwd.class));
		if(msg instanceof Boolean){
			return this.getReturnMapData(null,msg);
		}else {
			return this.getReturnMapData(msg==null?"null":msg.toString(),null);
		}
	}

}
