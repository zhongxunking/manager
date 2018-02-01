/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2018-02-01 23:13 创建
 */
package org.antframework.manager.web.common;

import org.antframework.boot.core.Contexts;
import org.antframework.common.util.facade.BizException;
import org.antframework.common.util.facade.CommonResultCode;
import org.antframework.common.util.facade.Status;
import org.antframework.manager.facade.api.RelationService;
import org.antframework.manager.facade.enums.ManagerType;
import org.antframework.manager.facade.info.ManagerInfo;
import org.antframework.manager.facade.order.FindRelationOrder;
import org.antframework.manager.facade.result.FindRelationResult;
import org.apache.commons.lang3.StringUtils;

/**
 * 管理员断言
 */
public class ManagerAssert {
    private static RelationService relationService = Contexts.getApplicationContext().getBean(RelationService.class);

    /**
     * 断言管理员已登陆并获取管理员信息
     */
    public static ManagerInfo currentManager() {
        ManagerInfo manager = ManagerSessionAccessor.getManager();
        if (manager == null) {
            throw new BizException(Status.FAIL, CommonResultCode.ILLEGAL_STATE.getCode(), "管理员未登陆");
        }
        return manager;
    }

    /**
     * 断言当前管理员为超级管理员
     */
    public static void admin() {
        ManagerInfo manager = currentManager();
        if (manager.getType() != ManagerType.ADMIN) {
            throw new BizException(Status.FAIL, CommonResultCode.ILLEGAL_STATE.getCode(), String.format("管理员[%s]不是超级管理员", manager.getManagerId()));
        }
    }

    /**
     * 断言当前管理员为超级管理员或为指定的管理员
     *
     * @param managerId 管理员id
     */
    public static void adminOrMyself(String managerId) {
        ManagerInfo manager = currentManager();
        if (manager.getType() != ManagerType.ADMIN && !StringUtils.equals(managerId, manager.getManagerId())) {
            throw new BizException(Status.FAIL, CommonResultCode.ILLEGAL_STATE.getCode(), String.format("管理员[%s]不是超级管理员且不是指定管理员[%s]", manager.getManagerId(), managerId));
        }
    }

    /**
     * 断言当前管理员为超级管理员或以目标有关系
     *
     * @param targetId 目标id
     */
    public static void adminOrHaveRelation(String targetId) {
        ManagerInfo manager = currentManager();
        if (manager.getType() == ManagerType.ADMIN) {
            return;
        }

        FindRelationOrder order = new FindRelationOrder();
        order.setManagerId(manager.getManagerId());
        order.setTargetId(targetId);

        FindRelationResult result = relationService.findRelation(order);
        if (!result.isSuccess()) {
            throw new BizException(Status.FAIL, result.getCode(), result.getMessage());
        }
        if (result.getRelationInfo() == null) {
            throw new BizException(Status.FAIL, CommonResultCode.ILLEGAL_STATE.getCode(), String.format("管理员%s与%s不存在关系", manager.getManagerId(), targetId));
        }
    }
}
