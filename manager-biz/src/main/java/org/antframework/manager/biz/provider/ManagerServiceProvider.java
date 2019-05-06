/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2018-01-24 09:06 创建
 */
package org.antframework.manager.biz.provider;

import lombok.AllArgsConstructor;
import org.antframework.boot.bekit.CommonQueries;
import org.antframework.common.util.facade.EmptyResult;
import org.antframework.manager.biz.util.QueryUtils;
import org.antframework.manager.dal.dao.ManagerDao;
import org.antframework.manager.facade.api.ManagerService;
import org.antframework.manager.facade.order.*;
import org.antframework.manager.facade.result.FindManagerResult;
import org.antframework.manager.facade.result.ManagerLoginResult;
import org.antframework.manager.facade.result.QueryManagersResult;
import org.bekit.service.ServiceEngine;
import org.springframework.stereotype.Service;

/**
 * 管理员服务提供者
 */
@Service
@AllArgsConstructor
public class ManagerServiceProvider implements ManagerService {
    // 服务引擎
    private final ServiceEngine serviceEngine;

    @Override
    public EmptyResult addManager(AddManagerOrder order) {
        return serviceEngine.execute("addManagerService", order);
    }

    @Override
    public EmptyResult modifyManagerType(ModifyManagerTypeOrder order) {
        return serviceEngine.execute("modifyManagerTypeService", order);
    }

    @Override
    public EmptyResult modifyManagerName(ModifyManagerNameOrder order) {
        return serviceEngine.execute("modifyManagerNameService", order);
    }

    @Override
    public EmptyResult modifyManagerPassword(ModifyManagerPasswordOrder order) {
        return serviceEngine.execute("modifyManagerPasswordService", order);
    }

    @Override
    public EmptyResult deleteManager(DeleteManagerOrder order) {
        return serviceEngine.execute("deleteManagerService", order);
    }

    @Override
    public FindManagerResult findManager(FindManagerOrder order) {
        return serviceEngine.execute("findManagerService", order);
    }

    @Override
    public QueryManagersResult queryManagers(QueryManagersOrder order) {
        CommonQueries.CommonQueryResult result = serviceEngine.execute(CommonQueries.SERVICE_NAME, order, QueryUtils.buildCommonQueryAttachment(ManagerDao.class));
        return result.convertTo(QueryManagersResult.class);
    }

    @Override
    public ManagerLoginResult managerLogin(ManagerLoginOrder order) {
        return serviceEngine.execute("managerLoginService", order);
    }
}
