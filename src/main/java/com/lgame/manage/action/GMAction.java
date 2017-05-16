package com.lgame.manage.action;


import com.lgame.codec.BdResponse;
import com.lgame.manage.cache.GmUserSessionManager;
import com.lgame.manage.cache.ServerConnection;
import com.lgame.manage.cache.ServerManager;
import com.lgame.manage.service.FileService;
import com.lgame.manage.service.LoginService;
import com.lgame.model.*;
import com.lgame.model.net.CmdEnum;
import com.lgame.model.net.CmdMsg;
import com.lgame.util.comm.StringTool;
import com.lgame.util.json.JsonUtil;
import com.lsocket.core.ClientServer;
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

		int mcd_c = CMDManager.getCmd_M(msg.getModule(),msg.getCmd());
		CmdEnum cmdEnum = CmdEnum.datas.get(mcd_c);
		if(cmdEnum == null){
			return  "gm not config cmd:"+msg.getCmd();
		}

		if(StringTool.isEmpty(msg.getKey())){
			try {
				Map<String,Object> map = new HashMap<>();
				map.put("obj",cmdEnum.getCls().newInstance());
				map.put("json",JsonUtil.getJsonFromBean(map.get("obj")));
				return map;
			} catch (Exception e) {
				e.printStackTrace();
				return "服务器异常:"+e.getMessage();
			}
		}

		ClientServer clientServer = GmUserSessionManager.clenets.get(msg.getUid());
		if(clientServer == null){
			return "uid:"+msg.getUid()+" not login";
		}

		Object obj = JsonUtil.getBeanFromJson(msg.getMsg(),cmdEnum.getCls());

		synchronized (clientServer){
			BdResponse response = new BdResponse();
			response.setBaiduObj(obj);
			response.setCmd(mcd_c);
			GmUserSessionManager.getInstance().sendMsg(msg.getUid(),response);
			try {
				clientServer.wait(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		return GmUserSessionManager.getInstance().getMsg(msg.getUid());
	}

	@RequestMapping(value={"/first"},method = RequestMethod.POST)
	@ResponseBody
	public Object first(CmdMsg msg, HttpServletRequest request, HttpSession session){
		CmdEnum cmdEnum = CmdEnum.FirstConnect;

		System.out.println("==login:cmd:"+cmdEnum.getCmd());
		Object obj = null;
        if(!StringTool.isEmpty(msg.getMsg())){
            obj = JsonUtil.getBeanFromJson(msg.getMsg(),cmdEnum.getCls());
        }

		ServerConnection serverConnection = ServerManager.getIntance().getServerConnection(msg.getServerId());
		if(serverConnection == null){
			return "cant not find  serverId:"+msg.getServerId();
		}else {
			System.out.println("serverId"+msg.getServerId()+" status:"+serverConnection.getRunStatus());
		}
		boolean isSuc = GmUserSessionManager.getInstance().connect(msg.getUid(),msg.getKey(),serverConnection);
        if(!isSuc){
            return "connect:id"+msg.getServerId()+"  ip:"+serverConnection.getIp()+":"+serverConnection.getPort()+" failed";
        }

		ClientServer clientServer = GmUserSessionManager.clenets.get(msg.getUid());
		if(clientServer == null){
			return "uid:"+msg.getUid()+" not login";
		}
		synchronized (clientServer){
			BdResponse response = new BdResponse();
			response.setBaiduObj(obj);
			response.setCmd(cmdEnum.getCmd());
			GmUserSessionManager.getInstance().sendMsg(msg.getUid(),response);
			try {
				clientServer.wait(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		return GmUserSessionManager.getInstance().getMsg(msg.getUid());
	}
}
