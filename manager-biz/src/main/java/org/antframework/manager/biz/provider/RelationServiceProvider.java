/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2018-01-25 21:19 创建
 */
package org.antframework.manager.biz.provider;

import org.antframework.common.util.facade.EmptyResult;
import org.antframework.manager.facade.api.RelationService;
import org.antframework.manager.facade.order.AddRelationOrder;
import org.antframework.manager.facade.order.DeleteRelationOrder;
import org.bekit.service.ServiceEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 关系服务提供者
 */
@Service
public class RelationServiceProvider implements RelationService {
    @Autowired
    private ServiceEngine serviceEngine;

    @Override
    public EmptyResult addRelation(AddRelationOrder order) {
        return serviceEngine.execute("addRelationService", order);
    }

    @Override
    public EmptyResult deleteRelation(DeleteRelationOrder order) {
        return serviceEngine.execute("deleteRelationService", order);
    }
}
