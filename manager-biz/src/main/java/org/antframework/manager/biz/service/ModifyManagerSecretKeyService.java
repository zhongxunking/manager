/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2019-06-30 17:15 创建
 */
package org.antframework.manager.biz.service;

import lombok.AllArgsConstructor;
import org.antframework.common.util.facade.BizException;
import org.antframework.common.util.facade.CommonResultCode;
import org.antframework.common.util.facade.EmptyResult;
import org.antframework.common.util.facade.Status;
import org.antframework.manager.dal.dao.ManagerDao;
import org.antframework.manager.dal.entity.Manager;
import org.antframework.manager.facade.order.ModifyManagerSecretKeyOrder;
import org.bekit.service.annotation.service.Service;
import org.bekit.service.annotation.service.ServiceExecute;
import org.bekit.service.engine.ServiceContext;

/**
 * 修改管理员的密钥服务
 */
@Service(enableTx = true)
@AllArgsConstructor
public class ModifyManagerSecretKeyService {
    // 管理员dao
    private final ManagerDao managerDao;

    @ServiceExecute
    public void execute(ServiceContext<ModifyManagerSecretKeyOrder, EmptyResult> context) {
        ModifyManagerSecretKeyOrder order = context.getOrder();

        Manager manager = managerDao.findLockByManagerId(order.getManagerId());
        if (manager == null) {
            throw new BizException(Status.FAIL, CommonResultCode.INVALID_PARAMETER.getCode(), String.format("管理员[%s]不存在", order.getManagerId()));
        }
        manager.setSecretKey(order.getSecretKey());
        managerDao.save(manager);
    }
}
