package com.lgame.manage.action;


import com.lgame.manage.service.FileService;
import com.lgame.manage.service.LoginService;
import com.lgame.model.*;
import com.lgame.model.net.CmdEnum;
import com.lgame.model.net.CmdMsg;
import com.lgame.util.comm.StringTool;
import com.lgame.util.json.JsonUtil;
import com.lsocket.manager.CMDManager;
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

@RequestMapping("/gm")
@Controller
public class GMAction {
	@Autowired
	private LoginService loginService;
	@Autowired
	private FileService fileService;

	/**
	 * 跳转登陆页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value={"/ts"},method=RequestMethod.GET)
	public String test(HttpServletRequest request, HttpServletResponse response){
		return "test/version";
	}

	@RequestMapping(value={"/testmsg"},method = RequestMethod.POST)
	@ResponseBody
	public Object testMsg(CmdMsg msg, HttpServletRequest request, HttpSession session){
		int mcd_c = CMDManager.getCmd_M(msg.getModlue(),msg.getCmd());
		CmdEnum cmdEnum = CmdEnum.datas.get(mcd_c);
		if(cmdEnum == null){
			return  "gm not config cmd:"+msg.getCmd();
		}

		if(StringTool.isEmpty(msg.getKey())){
			try {
				return cmdEnum.getCls().newInstance();
			} catch (Exception e) {
				e.printStackTrace();
				return "服务器异常:"+e.getMessage();
			}
		}

		Object obj = JsonUtil.getBeanFromJson(msg.getMsg(),cmdEnum.getCls());

		synchronized (CmdMsg.class){
			try {
				CmdMsg.class.wait(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		return "正在请求...";
	}
}
