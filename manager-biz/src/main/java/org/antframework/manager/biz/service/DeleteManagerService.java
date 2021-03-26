/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2018-01-24 09:19 创建
 */
package org.antframework.manager.biz.service;

import lombok.AllArgsConstructor;
import org.antframework.common.util.facade.EmptyResult;
import org.antframework.common.util.facade.FacadeUtils;
import org.antframework.manager.dal.dao.ManagerDao;
import org.antframework.manager.dal.entity.Manager;
import org.antframework.manager.facade.event.ManagerDeletingEvent;
import org.antframework.manager.facade.info.ManagerInfo;
import org.antframework.manager.facade.order.DeleteManagerOrder;
import org.bekit.event.EventPublisher;
import org.bekit.service.annotation.service.Service;
import org.bekit.service.annotation.service.ServiceExecute;
import org.bekit.service.engine.ServiceContext;
import org.springframework.core.convert.converter.Converter;

/**
 * 删除管理员服务
 */
@Service(enableTx = true)
@AllArgsConstructor
public class DeleteManagerService {
    // info转换器
    private static final Converter<Manager, ManagerInfo> INFO_CONVERTER = new FacadeUtils.DefaultConverter<>(ManagerInfo.class);

    // 管理员dao
    private final ManagerDao managerDao;
    // 事件发布器
    private final EventPublisher eventPublisher;

    @ServiceExecute
    public void execute(ServiceContext<DeleteManagerOrder, EmptyResult> context) {
        DeleteManagerOrder order = context.getOrder();

        Manager manager = managerDao.findLockByManagerId(order.getManagerId());
        if (manager == null) {
            return;
        }
        managerDao.delete(manager);
        // 发送事件
        eventPublisher.publish(new ManagerDeletingEvent(INFO_CONVERTER.convert(manager)));
    }
}
