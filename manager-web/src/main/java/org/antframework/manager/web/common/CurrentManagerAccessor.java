/* 
 * 作者：钟勋 (email:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2021-03-22 20:42 创建
 */
package org.antframework.manager.web.common;

import org.antframework.manager.facade.info.ManagerInfo;

/**
 * 当前管理员访问器
 */
public class CurrentManagerAccessor {
    // 临时当前管理员持有器
    private static final ThreadLocal<ManagerInfo> TRANSIENT_CURRENT_MANAGER_HOLDER = new ThreadLocal<>();

    /**
     * 设置临时当前管理员
     *
     * @param manager 管理员
     */
    public static void setTransientCurrentManager(ManagerInfo manager) {
        if (manager != null) {
            TRANSIENT_CURRENT_MANAGER_HOLDER.set(manager);
        } else {
            TRANSIENT_CURRENT_MANAGER_HOLDER.remove();
        }
    }

    /**
     * 获取当前管理员
     */
    public static ManagerInfo getCurrentManager() {
        ManagerInfo manager = TRANSIENT_CURRENT_MANAGER_HOLDER.get();
        if (manager == null) {
            manager = CurrentManagers.current();
        }
        return manager;
    }
}
