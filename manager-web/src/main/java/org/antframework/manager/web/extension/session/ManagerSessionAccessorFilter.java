/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2018-01-29 23:21 创建
 */
package org.antframework.manager.web.extension.session;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 管理员Session访问器Filter
 */
public class ManagerSessionAccessorFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ManagerSessionAccessor.setRequest((HttpServletRequest) request);
        try {
            chain.doFilter(request, response);
        } finally {
            ManagerSessionAccessor.setRequest(null);
        }
    }
}
