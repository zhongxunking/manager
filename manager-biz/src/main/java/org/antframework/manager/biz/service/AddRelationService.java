/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2018-01-25 21:22 创建
 */
package org.antframework.manager.biz.service;

import org.antframework.common.util.facade.BizException;
import org.antframework.common.util.facade.CommonResultCode;
import org.antframework.common.util.facade.EmptyResult;
import org.antframework.common.util.facade.Status;
import org.antframework.manager.dal.dao.ManagerDao;
import org.antframework.manager.dal.dao.RelationDao;
import org.antframework.manager.dal.entity.Manager;
import org.antframework.manager.dal.entity.Relation;
import org.antframework.manager.facade.order.AddRelationOrder;
import org.bekit.service.annotation.service.Service;
import org.bekit.service.annotation.service.ServiceExecute;
import org.bekit.service.engine.ServiceContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 新增关系服务
 */
@Service(enableTx = true)
public class AddRelationService {
    @Autowired
    private ManagerDao managerDao;
    @Autowired
    private RelationDao relationDao;

    @ServiceExecute
    public void execute(ServiceContext<AddRelationOrder, EmptyResult> context) {
        AddRelationOrder order = context.getOrder();

        Manager manager = managerDao.findLockByManagerId(order.getManagerId());
        if (manager == null) {
            throw new BizException(Status.FAIL, CommonResultCode.INVALID_PARAMETER.getCode(), String.format("管理员[%s]不存在", order.getManagerId()));
        }
        Relation relation = relationDao.findLockByManagerIdAndTargetId(order.getManagerId(), order.getTargetId());
        if (relation == null) {
            relation = buildRelation(order);
            relationDao.save(relation);
        }
    }

    // 构建关系
    private Relation buildRelation(AddRelationOrder addRelationOrder) {
        Relation relation = new Relation();
        BeanUtils.copyProperties(addRelationOrder, relation);
        return relation;
    }
}
