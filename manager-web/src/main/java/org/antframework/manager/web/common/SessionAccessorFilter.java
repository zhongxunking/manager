/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2018-01-29 23:21 创建
 */
package org.antframework.manager.web.common;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * session访问器设置filter
 */
public class SessionAccessorFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ManagerSessionAccessor.setSession(((HttpServletRequest) request).getSession());
        chain.doFilter(request, response);
        ManagerSessionAccessor.removeSession();
    }

    @Override
    public void destroy() {
    }
}
