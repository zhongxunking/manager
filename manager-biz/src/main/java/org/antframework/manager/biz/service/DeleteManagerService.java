/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2018-01-24 09:19 创建
 */
package org.antframework.manager.biz.service;

import org.antframework.common.util.facade.EmptyResult;
import org.antframework.manager.dal.dao.ManagerDao;
import org.antframework.manager.dal.entity.Manager;
import org.antframework.manager.facade.order.DeleteManagerOrder;
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

    @ServiceExecute
    public void execute(ServiceContext<DeleteManagerOrder, EmptyResult> context) {
        DeleteManagerOrder order = context.getOrder();

        Manager manager = managerDao.findLockByManagerId(order.getManagerId());
        if (manager == null) {
            return;
        }
        managerDao.delete(manager);
    }
}
