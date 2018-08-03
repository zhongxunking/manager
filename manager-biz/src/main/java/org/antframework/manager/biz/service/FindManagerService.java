/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2018-08-04 01:05 创建
 */
package org.antframework.manager.biz.service;

import org.antframework.common.util.facade.FacadeUtils;
import org.antframework.manager.dal.dao.ManagerDao;
import org.antframework.manager.dal.entity.Manager;
import org.antframework.manager.facade.info.ManagerInfo;
import org.antframework.manager.facade.order.FindManagerOrder;
import org.antframework.manager.facade.result.FindManagerResult;
import org.bekit.service.annotation.service.Service;
import org.bekit.service.annotation.service.ServiceExecute;
import org.bekit.service.engine.ServiceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

/**
 * 查找管理员服务
 */
@Service
public class FindManagerService {
    private static final Converter<Manager, ManagerInfo> INFO_CONVERTER = new FacadeUtils.DefaultConverter<>(ManagerInfo.class);
    @Autowired
    private ManagerDao managerDao;

    @ServiceExecute
    public void execute(ServiceContext<FindManagerOrder, FindManagerResult> context) {
        FindManagerOrder order = context.getOrder();

        Manager manager = managerDao.findByManagerId(order.getManagerId());
        if (manager != null) {
            context.getResult().setManager(INFO_CONVERTER.convert(manager));
        }
    }
}
