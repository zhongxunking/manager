/* 
 * 作者：钟勋 (email:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2021-03-22 22:12 创建
 */
package org.antframework.manager.web.extension.session;

import org.antframework.common.util.facade.BizException;
import org.antframework.manager.biz.util.Managers;
import org.antframework.manager.facade.info.ManagerInfo;
import org.antframework.manager.web.extension.CurrentManagerService;
import org.springframework.stereotype.Service;

/**
 * 基于Session的当前管理员服务
 */
@Service
public class SessionCurrentManagerService implements CurrentManagerService {
    // 管理员在Session中的属性名
    private static final String SESSION_MANAGER_NAME = "manager";

    @Override
    public ManagerInfo login(String managerId, String password) {
        try {
            Managers.validateManagerPassword(managerId, password);
        } catch (BizException e) {
            return null;
        }
        ManagerInfo manager = Managers.findManager(managerId);
        ManagerSessionAccessor.setAttribute(SESSION_MANAGER_NAME, manager);
        return manager;
    }

    @Override
    public void logout() {
        ManagerSessionAccessor.setAttribute(SESSION_MANAGER_NAME, null);
    }

    @Override
    public ManagerInfo current() {
        return ManagerSessionAccessor.getAttribute(SESSION_MANAGER_NAME);
    }

    @Override
    public void refreshCurrent() {
        ManagerInfo manager = current();
        if (manager != null) {
            manager = Managers.findManager(manager.getManagerId());
            ManagerSessionAccessor.setAttribute(SESSION_MANAGER_NAME, manager);
        }
    }
}
