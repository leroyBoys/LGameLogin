package com.lgame.filter;

import com.lgame.utils.AppException;
import com.logger.log.SystemLogger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;


public class RepeateRequsetFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    private void write(ServletResponse response,String msg){
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {
            System.out.println("========================msg=====================:"+msg);
            PrintWriter writer = response.getWriter();
            writer.write("{\"error\":\""+msg+"\"}");
            writer.flush();
        } catch (IOException e) {
        }
    }

    public void doFilter(ServletRequest req, ServletResponse resp,FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        long startTime = System.currentTimeMillis();
        String uri = request.getServletPath();

        boolean isLog =uri.startsWith("/gm");
        if(!isLog){
            SystemLogger.info(this.getClass(),"receive:"+uri);
        }

        try {
            if(session.getAttribute(uri) != null){
                write(resp,"请求过于频繁!");
                return;
            }
            session.setAttribute(uri,1);
            chain.doFilter(req, resp);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.removeAttribute(uri);
            if(!isLog){
                SystemLogger.info(this.getClass(),uri+":耗时:"+(System.currentTimeMillis()-startTime)+" ms");
            }
        }

    }

    @Override
    public void destroy() {
    }
}
