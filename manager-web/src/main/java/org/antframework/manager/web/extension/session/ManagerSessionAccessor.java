/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2018-01-28 14:46 创建
 */
package org.antframework.manager.web.extension.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 管理员Session访问器
 */
public class ManagerSessionAccessor {
    // Request持有器
    private static final ThreadLocal<HttpServletRequest> REQUEST_HOLDER = new ThreadLocal<>();

    /**
     * 设置Request
     */
    static void setRequest(HttpServletRequest request) {
        if (request != null) {
            REQUEST_HOLDER.set(request);
        } else {
            REQUEST_HOLDER.remove();
        }
    }

    /**
     * 获取Session
     *
     * @param createIfNecessary 是否创建（如果有必要）
     * @return Session（null表示不存在Session）
     */
    public static HttpSession getSession(boolean createIfNecessary) {
        HttpServletRequest request = REQUEST_HOLDER.get();
        if (request == null) {
            throw new IllegalStateException("request为null");
        }
        return request.getSession(createIfNecessary);
    }

    /**
     * 设置属性
     *
     * @param name  属性名
     * @param value 属性值
     */
    public static void setAttribute(String name, Object value) {
        if (value != null) {
            HttpSession session = getSession(true);
            session.setAttribute(name, value);
        } else {
            HttpSession session = getSession(false);
            if (session != null) {
                session.removeAttribute(name);
            }
        }
    }

    /**
     * 获取属性值
     *
     * @param name 属性名
     * @param <T>  属性类型
     * @return 属性值
     */
    public static <T> T getAttribute(String name) {
        T value = null;
        HttpSession session = getSession(false);
        if (session != null) {
            value = (T) session.getAttribute(name);
        }
        return value;
    }
}
