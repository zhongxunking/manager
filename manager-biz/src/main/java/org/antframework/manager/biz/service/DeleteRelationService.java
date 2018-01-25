/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2018-01-25 21:34 创建
 */
package org.antframework.manager.biz.service;

import org.antframework.common.util.facade.EmptyResult;
import org.antframework.common.util.query.QueryOperator;
import org.antframework.common.util.query.QueryParam;
import org.antframework.manager.dal.dao.RelationDao;
import org.antframework.manager.dal.entity.Relation;
import org.antframework.manager.facade.order.DeleteRelationOrder;
import org.bekit.service.annotation.service.Service;
import org.bekit.service.annotation.service.ServiceExecute;
import org.bekit.service.engine.ServiceContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 删除关系服务
 */
@Service(enableTx = true)
public class DeleteRelationService {
    @Autowired
    private RelationDao relationDao;

    @ServiceExecute
    public void execute(ServiceContext<DeleteRelationOrder, EmptyResult> context) {
        DeleteRelationOrder order = context.getOrder();

        List<Relation> relations = relationDao.query(buildQueryParams(order));
        for (Relation relation : relations) {
            relationDao.delete(relation);
        }
    }

    // 构建查询参数
    private Collection<QueryParam> buildQueryParams(DeleteRelationOrder deleteRelationOrder) {
        List<QueryParam> queryParams = new ArrayList<>();
        if (deleteRelationOrder.getManagerId() != null) {
            queryParams.add(new QueryParam("managerId", QueryOperator.EQ, deleteRelationOrder.getManagerId()));
        }
        if (deleteRelationOrder.getTargetId() != null) {
            queryParams.add(new QueryParam("targetId", QueryOperator.EQ, deleteRelationOrder.getTargetId()));
        }
        return queryParams;
    }
}
