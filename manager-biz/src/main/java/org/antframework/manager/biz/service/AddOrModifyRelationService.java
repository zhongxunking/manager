/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2018-01-25 21:22 创建
 */
package org.antframework.manager.biz.service;

import org.antframework.common.util.facade.EmptyResult;
import org.antframework.manager.dal.dao.RelationDao;
import org.antframework.manager.dal.entity.Relation;
import org.antframework.manager.facade.order.AddOrModifyRelationOrder;
import org.bekit.service.annotation.service.Service;
import org.bekit.service.annotation.service.ServiceExecute;
import org.bekit.service.engine.ServiceContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 新增或修改关系服务
 */
@Service(enableTx = true)
public class AddOrModifyRelationService {
    @Autowired
    private RelationDao relationDao;

    @ServiceExecute
    public void execute(ServiceContext<AddOrModifyRelationOrder, EmptyResult> context) {
        AddOrModifyRelationOrder order = context.getOrder();

        Relation relation = relationDao.findLockByTypeAndSourceAndTarget(order.getType(), order.getSource(), order.getTarget());
        if (relation == null) {
            relation = new Relation();
        }
        BeanUtils.copyProperties(order, relation);
        relationDao.save(relation);
    }
}
