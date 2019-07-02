/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2018-02-01 23:13 创建
 */
package org.antframework.manager.web;

import org.antframework.boot.core.Contexts;
import org.antframework.common.util.facade.BizException;
import org.antframework.common.util.facade.CommonResultCode;
import org.antframework.common.util.facade.FacadeUtils;
import org.antframework.common.util.facade.Status;
import org.antframework.manager.facade.api.ManagerService;
import org.antframework.manager.facade.enums.ManagerType;
import org.antframework.manager.facade.info.ManagerInfo;
import org.antframework.manager.facade.order.FindManagerOrder;
import org.antframework.manager.facade.result.FindManagerResult;
import org.antframework.manager.web.common.ManagerSessionAccessor;

import java.util.Objects;

/**
 * 当前登录的管理员工具类
 */
public final class CurrentManagers {
    // 管理员服务
    private static final ManagerService MANAGER_SERVICE = Contexts.getApplicationContext().getBean(ManagerService.class);

    /**
     * 断言管理员已登陆并获取管理员信息
     */
    public static ManagerInfo current() {
        ManagerInfo manager = ManagerSessionAccessor.getManager();
        if (manager == null) {
            throw new BizException(Status.FAIL, CommonResultCode.UNAUTHORIZED.getCode(), CommonResultCode.UNAUTHORIZED.getMessage());
        }
        return manager;
    }

    /**
     * 断言当前管理员为超级管理员
     */
    public static void admin() {
        ManagerInfo manager = current();
        if (manager.getType() != ManagerType.ADMIN) {
            throw new BizException(Status.FAIL, CommonResultCode.ILLEGAL_STATE.getCode(), String.format("当前管理员[%s]不是超级管理员", manager.getManagerId()));
        }
    }

    /**
     * 断言当前管理员为指定的管理员
     *
     * @param managerId 管理员id
     */
    public static void myself(String managerId) {
        ManagerInfo manager = current();
        if (!Objects.equals(managerId, manager.getManagerId())) {
            throw new BizException(Status.FAIL, CommonResultCode.ILLEGAL_STATE.getCode(), String.format("当前管理员[%s]不是指定管理员[%s]", manager.getManagerId(), managerId));
        }
    }

    /**
     * 断言当前管理员为超级管理员或为指定的管理员
     *
     * @param managerId 管理员id
     */
    public static void adminOrMyself(String managerId) {
        try {
            admin();
        } catch (BizException adminE) {
            try {
                myself(managerId);
            } catch (BizException myselfE) {
                ManagerInfo manager = current();
                throw new BizException(Status.FAIL, CommonResultCode.ILLEGAL_STATE.getCode(), String.format("当前管理员[%s]既不是超级管理员也不是指定管理员[%s]", manager.getManagerId(), managerId));
            }
        }
    }

    /**
     * 查找管理员
     *
     * @param managerId 管理员id
     * @return 管理员（null表示无该管理员）
     */
    public static ManagerInfo findManager(String managerId) {
        FindManagerOrder order = new FindManagerOrder();
        order.setManagerId(managerId);

        FindManagerResult result = MANAGER_SERVICE.findManager(order);
        FacadeUtils.assertSuccess(result);
        return result.getManager();
    }
}
