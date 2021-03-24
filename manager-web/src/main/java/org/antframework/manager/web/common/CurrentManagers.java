/* 
 * 作者：钟勋 (email:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2021-03-24 13:50 创建
 */
package org.antframework.manager.web.common;

import org.antframework.boot.core.Contexts;
import org.antframework.manager.facade.info.ManagerInfo;
import org.antframework.manager.web.extension.CurrentManagerService;

/**
 * 当前管理员工具
 */
public class CurrentManagers {
    // 当前管理员服务
    private static final CurrentManagerService CURRENT_MANAGER_SERVICE = Contexts.getApplicationContext().getBean(CurrentManagerService.class);

    /**
     * 登陆
     *
     * @param managerId 管理员id
     * @param password  密码
     * @return 管理员（null表示管理员不存在或密码不正确）
     */
    public static ManagerInfo login(String managerId, String password) {
        return CURRENT_MANAGER_SERVICE.login(managerId, password);
    }

    /**
     * 退出登陆
     */
    public static void logout() {
        CURRENT_MANAGER_SERVICE.logout();
    }

    /**
     * 获取当前管理员
     *
     * @return 当前管理员（null表示未登录）
     */
    public static ManagerInfo current() {
        return CURRENT_MANAGER_SERVICE.current();
    }


    /**
     * 刷新当前管理员
     */
    public static void refreshCurrent() {
        CURRENT_MANAGER_SERVICE.refreshCurrent();
    }
}
