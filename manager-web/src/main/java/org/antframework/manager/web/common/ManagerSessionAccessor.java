/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2018-01-28 14:46 创建
 */
package org.antframework.manager.web.common;

import org.antframework.manager.facade.info.ManagerInfo;

import javax.servlet.http.HttpSession;

/**
 * session中管理员访问器
 */
public class ManagerSessionAccessor {
    // session持有器
    private static final ThreadLocal<HttpSession> SESSION_HOLDER = new ThreadLocal<>();
    // 管理员在session中的key
    private static final String KEY_MANAGER = "KEY_MANAGER";

    /**
     * 设置session
     */
    static void setSession(HttpSession session) {
        SESSION_HOLDER.set(session);
    }

    /**
     * 删除session
     */
    static void removeSession() {
        SESSION_HOLDER.remove();
    }

    /**
     * 向session设置管理员
     *
     * @param manager 管理员（null表示删除session中的管理员）
     */
    public static void setManager(ManagerInfo manager) {
        if (manager != null) {
            SESSION_HOLDER.get().setAttribute(KEY_MANAGER, manager);
        } else {
            SESSION_HOLDER.get().removeAttribute(KEY_MANAGER);
        }
    }

    /**
     * 从session获取管理员
     */
    public static ManagerInfo getManager() {
        return (ManagerInfo) SESSION_HOLDER.get().getAttribute(KEY_MANAGER);
    }
}
