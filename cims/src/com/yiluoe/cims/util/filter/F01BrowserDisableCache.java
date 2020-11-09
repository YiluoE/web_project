package com.yiluoe.cims.util.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: cims
 * @description 关闭浏览器自动缓存
 * @user: 本以罗伊斯
 * @create: 2020/11/7 9:15
 **/

@WebFilter
public class F01BrowserDisableCache implements Filter {

    /**
     * 在响应头里添加（关闭浏览器自动缓存）标识后放行
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        response.setHeader("Cache-Control","no-cache");
        response.setHeader("Pragma","no-cache");
        response.setDateHeader("Expires",-1);

        filterChain.doFilter(servletRequest,servletResponse);
    }

}
