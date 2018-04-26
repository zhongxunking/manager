/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2018-01-26 09:45 创建
 */
package org.antframework.manager.biz.service;

import org.antframework.boot.bekit.CommonQueries;
import org.antframework.common.util.facade.BizException;
import org.antframework.common.util.facade.CommonResultCode;
import org.antframework.common.util.facade.Status;
import org.antframework.manager.biz.util.QueryUtils;
import org.antframework.manager.dal.dao.ManagerDao;
import org.antframework.manager.dal.dao.RelationDao;
import org.antframework.manager.dal.entity.Manager;
import org.antframework.manager.facade.order.QueryManagerRelationOrder;
import org.antframework.manager.facade.result.QueryManagerRelationResult;
import org.bekit.service.ServiceEngine;
import org.bekit.service.annotation.service.Service;
import org.bekit.service.annotation.service.ServiceBefore;
import org.bekit.service.annotation.service.ServiceExecute;
import org.bekit.service.engine.ServiceContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 查询指定管理员相关的关系服务
 */
@Service
public class QueryManagerRelationService {
    @Autowired
    private ManagerDao managerDao;
    @Autowired
    private ServiceEngine serviceEngine;

    @ServiceBefore
    public void before(ServiceContext<QueryManagerRelationOrder, QueryManagerRelationResult> context) {
        QueryManagerRelationOrder order = context.getOrder();

        Manager manager = managerDao.findByManagerId(order.getManagerId());
        if (manager == null) {
            throw new BizException(Status.FAIL, CommonResultCode.INVALID_PARAMETER.getCode(), String.format("管理员[%s]不存在", order.getManagerId()));
        }
    }

    @ServiceExecute
    public void execute(ServiceContext<QueryManagerRelationOrder, QueryManagerRelationResult> context) {
        QueryManagerRelationOrder order = context.getOrder();
        QueryManagerRelationResult result = context.getResult();

        CommonQueries.CommonQueryResult commonQueryResult = serviceEngine.execute(CommonQueries.SERVICE_NAME, order, QueryUtils.buildCommonQueryAttachment(RelationDao.class));
        commonQueryResult.convertTo(result, null);
    }
}
