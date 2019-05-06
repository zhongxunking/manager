/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2018-01-25 21:19 创建
 */
package org.antframework.manager.biz.provider;

import lombok.AllArgsConstructor;
import org.antframework.boot.bekit.CommonQueries;
import org.antframework.common.util.facade.EmptyResult;
import org.antframework.manager.biz.util.QueryUtils;
import org.antframework.manager.dal.dao.RelationDao;
import org.antframework.manager.facade.api.RelationService;
import org.antframework.manager.facade.order.*;
import org.antframework.manager.facade.result.FindRelationResult;
import org.antframework.manager.facade.result.QueryRelationsResult;
import org.antframework.manager.facade.result.QuerySourceRelationsResult;
import org.bekit.service.ServiceEngine;
import org.springframework.stereotype.Service;

/**
 * 关系服务提供者
 */
@Service
@AllArgsConstructor
public class RelationServiceProvider implements RelationService {
    // 服务引擎
    private final ServiceEngine serviceEngine;

    @Override
    public EmptyResult addOrModifyRelation(AddOrModifyRelationOrder order) {
        return serviceEngine.execute("addOrModifyRelationService", order);
    }

    @Override
    public EmptyResult deleteRelations(DeleteRelationsOrder order) {
        return serviceEngine.execute("deleteRelationsService", order);
    }

    @Override
    public FindRelationResult findRelation(FindRelationOrder order) {
        return serviceEngine.execute("findRelationService", order);
    }

    @Override
    public QuerySourceRelationsResult querySourceRelations(QuerySourceRelationsOrder order) {
        CommonQueries.CommonQueryResult result = serviceEngine.execute(CommonQueries.SERVICE_NAME, order, QueryUtils.buildCommonQueryAttachment(RelationDao.class));
        return result.convertTo(QuerySourceRelationsResult.class);
    }

    @Override
    public QueryRelationsResult queryRelations(QueryRelationsOrder order) {
        CommonQueries.CommonQueryResult result = serviceEngine.execute(CommonQueries.SERVICE_NAME, order, QueryUtils.buildCommonQueryAttachment(RelationDao.class));
        return result.convertTo(QueryRelationsResult.class);
    }
}
