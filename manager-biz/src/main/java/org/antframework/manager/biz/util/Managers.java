/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2019-07-03 00:38 创建
 */
package org.antframework.manager.biz.util;

import org.antframework.boot.core.Contexts;
import org.antframework.common.util.facade.EmptyResult;
import org.antframework.common.util.facade.FacadeUtils;
import org.antframework.manager.facade.api.ManagerService;
import org.antframework.manager.facade.info.ManagerInfo;
import org.antframework.manager.facade.order.FindManagerOrder;
import org.antframework.manager.facade.order.ValidateManagerPasswordOrder;
import org.antframework.manager.facade.result.FindManagerResult;

/**
 * 管理员工具类
 */
public final class Managers {
    // 管理员服务
    private static final ManagerService MANAGER_SERVICE = Contexts.getApplicationContext().getBean(ManagerService.class);

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

    /**
     * 校验管理员密码
     *
     * @param managerId 管理员id
     * @param password  密码
     * @throws org.antframework.common.util.facade.BizException 如果校验不通过
     */
    public static void validateManagerPassword(String managerId, String password) {
        ValidateManagerPasswordOrder order = new ValidateManagerPasswordOrder();
        order.setManagerId(managerId);
        order.setPassword(password);

        EmptyResult result = MANAGER_SERVICE.validateManagerPassword(order);
        FacadeUtils.assertSuccess(result);
    }
}
