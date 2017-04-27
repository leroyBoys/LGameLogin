package com.lgame.base.action;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.WebUtils;

@Controller
public class BaseAction {
	@Autowired
	protected HttpServletRequest request;
	@Autowired
	protected HttpSession session;
	/**
	 * 获得以prefix为开头的参数的key-value集合 没有为空
	 * @param prefix
	 * @return
	 */
	protected Map<String,Object> getParameterMap(String prefix){
		return WebUtils.getParametersStartingWith(request, prefix);
	}
	/**
	 * 获得多个相同参数的value集合
	 * @param param
	 * @return
	 */
	protected String[] getParameters(String param){
		return request.getParameterValues(param);
	}
	/**
	 * 获得单个参数的value
	 * @param param
	 * @return
	 */
	protected String getParameter(String param){
		return request.getParameter(param);
	}

}