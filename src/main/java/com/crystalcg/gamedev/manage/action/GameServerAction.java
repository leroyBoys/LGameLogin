package com.crystalcg.gamedev.manage.action;


import com.crystalcg.gamedev.manage.service.FileService;
import com.crystalcg.gamedev.manage.service.LoginService;
import com.crystalcg.gamedev.model.User;
import com.crystalcg.gamedev.utils.AppException;
import com.crystalcg.gamedev.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RequestMapping("/gameserver")
@Controller
public class GameServerAction {
	@Autowired
	private LoginService loginService;
	@Autowired
	private FileService fileService;


	/**
	 * 游戏登录处理
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping(value={})
	@ResponseBody
	public String login(User user, HttpServletRequest request,HttpSession session){
		System.out.println("=================>");
		return "redirect:login";
	}

	@RequestMapping(value = "/upload")
	@ResponseBody
	public String upload(@RequestParam MultipartFile file) throws Exception {
		return fileService.upLoad(file);
	}
}