/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2018-01-24 09:19 创建
 */
package org.antframework.manager.biz.service;

import org.antframework.common.util.facade.BizException;
import org.antframework.common.util.facade.EmptyResult;
import org.antframework.manager.dal.dao.ManagerDao;
import org.antframework.manager.dal.entity.Manager;
import org.antframework.manager.facade.api.RelationService;
import org.antframework.manager.facade.order.DeleteManagerOrder;
import org.antframework.manager.facade.order.DeleteRelationsOrder;
import org.bekit.service.annotation.service.Service;
import org.bekit.service.annotation.service.ServiceExecute;
import org.bekit.service.engine.ServiceContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 删除管理员服务
 */
@Service(enableTx = true)
public class DeleteManagerService {
    @Autowired
    private ManagerDao managerDao;
    @Autowired
    private RelationService relationService;

    @ServiceExecute
    public void execute(ServiceContext<DeleteManagerOrder, EmptyResult> context) {
        DeleteManagerOrder order = context.getOrder();

        Manager manager = managerDao.findLockByManagerId(order.getManagerId());
        if (manager == null) {
            return;
        }
        // 删除与管理员相关的关系
        EmptyResult deleteRelationsResult = relationService.deleteRelations(buildDeleteRelationsOrder(manager));
        if (!deleteRelationsResult.isSuccess()) {
            throw new BizException(deleteRelationsResult.getStatus(), deleteRelationsResult.getCode(), deleteRelationsResult.getMessage());
        }

        managerDao.delete(manager);
    }

    // 构建删除与管理员相关的关系order
    private DeleteRelationsOrder buildDeleteRelationsOrder(Manager manager) {
        DeleteRelationsOrder order = new DeleteRelationsOrder();
        order.setManagerId(manager.getManagerId());
        order.setTargetId(null);

        return order;
    }
}
