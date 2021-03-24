/* 
 * 作者：钟勋 (email:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2021-03-22 21:39 创建
 */
package org.antframework.manager.web.extension;

import org.antframework.manager.facade.info.ManagerInfo;

/**
 * 当前管理员服务
 */
public interface CurrentManagerService {
    /**
     * 登陆
     *
     * @param managerId 管理员id
     * @param password  密码
     * @return 管理员（null表示管理员不存在或密码不正确）
     */
    ManagerInfo login(String managerId, String password);

    /**
     * 退出登陆
     */
    void logout();

    /**
     * 获取当前管理员
     *
     * @return 当前管理员（null表示未登录）
     */
    ManagerInfo current();

    /**
     * 刷新当前管理员
     */
    void refreshCurrent();
}
