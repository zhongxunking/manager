/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2018-02-01 23:13 创建
 */
package org.antframework.manager.web;

import org.antframework.common.util.facade.BizException;
import org.antframework.common.util.facade.CommonResultCode;
import org.antframework.common.util.facade.Status;
import org.antframework.manager.facade.enums.ManagerType;
import org.antframework.manager.facade.info.ManagerInfo;
import org.antframework.manager.web.common.CurrentManagerAccessor;

import java.util.Objects;

/**
 * 当前管理员断言
 */
public final class CurrentManagerAssert {
    /**
     * 断言管理员已登陆并获取当前管理员
     */
    public static ManagerInfo current() {
        ManagerInfo manager = CurrentManagerAccessor.getCurrentManager();
        if (manager == null) {
            throw new BizException(Status.FAIL, CommonResultCode.UNAUTHORIZED.getCode(), CommonResultCode.UNAUTHORIZED.getMessage());
        }
        return manager;
    }

    /**
     * 断言当前管理员为超级管理员并获取管理员
     */
    public static ManagerInfo admin() {
        ManagerInfo manager = current();
        if (manager.getType() != ManagerType.ADMIN) {
            throw new BizException(Status.FAIL, CommonResultCode.UNAUTHORIZED.getCode(), String.format("当前管理员[%s]不是超级管理员", manager.getManagerId()));
        }
        return manager;
    }

    /**
     * 断言当前管理员为指定的管理员并获取管理员
     *
     * @param managerId 管理员id
     */
    public static ManagerInfo myself(String managerId) {
        ManagerInfo manager = current();
        if (!Objects.equals(managerId, manager.getManagerId())) {
            throw new BizException(Status.FAIL, CommonResultCode.UNAUTHORIZED.getCode(), String.format("当前管理员[%s]不是指定管理员[%s]", manager.getManagerId(), managerId));
        }
        return manager;
    }

    /**
     * 断言当前管理员为超级管理员或为指定的管理员并获取管理员
     *
     * @param managerId 管理员id
     */
    public static ManagerInfo adminOrMyself(String managerId) {
        ManagerInfo manager = current();
        if (manager.getType() != ManagerType.ADMIN && !Objects.equals(managerId, manager.getManagerId())) {
            throw new BizException(Status.FAIL, CommonResultCode.UNAUTHORIZED.getCode(), String.format("当前管理员[%s]既不是超级管理员也不是指定管理员[%s]", manager.getManagerId(), managerId));
        }
        return manager;
    }
}
