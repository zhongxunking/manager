/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2018-01-25 21:34 创建
 */
package org.antframework.manager.biz.service;

import org.antframework.common.util.facade.EmptyResult;
import org.antframework.common.util.query.annotation.QueryParamsParser;
import org.antframework.manager.dal.dao.RelationDao;
import org.antframework.manager.dal.entity.Relation;
import org.antframework.manager.facade.order.DeleteRelationsOrder;
import org.bekit.service.annotation.service.Service;
import org.bekit.service.annotation.service.ServiceExecute;
import org.bekit.service.engine.ServiceContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 删除关系服务
 */
@Service(enableTx = true)
public class DeleteRelationsService {
    @Autowired
    private RelationDao relationDao;

    @ServiceExecute
    public void execute(ServiceContext<DeleteRelationsOrder, EmptyResult> context) {
        DeleteRelationsOrder order = context.getOrder();

        List<Relation> relations = relationDao.query(QueryParamsParser.parse(order));
        for (Relation relation : relations) {
            relationDao.delete(relation);
        }
    }
}
