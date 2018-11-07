/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2018-01-25 21:17 创建
 */
package org.antframework.manager.facade.api;

import org.antframework.common.util.facade.EmptyResult;
import org.antframework.manager.facade.order.*;
import org.antframework.manager.facade.result.FindRelationResult;
import org.antframework.manager.facade.result.QueryRelationsResult;
import org.antframework.manager.facade.result.QuerySourceRelationsResult;

/**
 * 关系服务
 */
public interface RelationService {
    /**
     * 新增或修改关系
     */
    EmptyResult addOrModifyRelation(AddOrModifyRelationOrder order);

    /**
     * 删除关系
     */
    EmptyResult deleteRelations(DeleteRelationsOrder order);

    /**
     * 查找关系
     */
    FindRelationResult findRelation(FindRelationOrder order);

    /**
     * 查询指定源的关系
     */
    QuerySourceRelationsResult querySourceRelations(QuerySourceRelationsOrder order);

    /**
     * 查询关系
     */
    QueryRelationsResult queryRelations(QueryRelationsOrder order);
}
