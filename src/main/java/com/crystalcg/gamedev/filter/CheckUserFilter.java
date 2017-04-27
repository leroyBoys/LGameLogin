package com.crystalcg.gamedev.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.crystalcg.gamedev.utils.PropertiesUtils;


public class CheckUserFilter  implements Filter {
    private final Set<String> commonList = new HashSet<>();
    private final static String LOGIN_PREFIX = "/login";
    private static String LOGIN_URL = "/login";
    private static String RemoateGameServer = "/gameserver";

    public void init(FilterConfig filterConfig) throws ServletException {
        commonList.add("/static/css");
        commonList.add("/static/js");
        commonList.add("/static/images");
        commonList.add("/static/tool");
        commonList.add("/test");
        LOGIN_URL = filterConfig.getServletContext().getContextPath()+LOGIN_URL;
    }

    public void doFilter(ServletRequest req, ServletResponse resp,FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();
        String uri = request.getServletPath();
        if(uri.startsWith(LOGIN_PREFIX) || uri.startsWith(RemoateGameServer) ){
            chain.doFilter(req, resp);
            return;
        }

        boolean isWhite = uri.length() <= 1?false: commonList.contains(uri.substring(0,uri.lastIndexOf("/")));//白名单

        if(isWhite || session.getAttribute("cur_user")!=null){
            chain.doFilter(req, resp);
            return;
        }
        request.setAttribute("msg", "请先登录");
        response.sendRedirect(LOGIN_URL);//跳转到登陆页
    }

    @Override
    public void destroy() {
    }
}
