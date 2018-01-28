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
import org.antframework.manager.facade.result.QueryManagerRelationResult;
import org.antframework.manager.facade.result.QueryRelationResult;

/**
 * 关系服务
 */
public interface RelationService {
    /**
     * 新增关系
     */
    EmptyResult addRelation(AddRelationOrder order);

    /**
     * 删除关系
     */
    EmptyResult deleteRelation(DeleteRelationOrder order);

    /**
     * 查找关系
     */
    FindRelationResult findRelation(FindRelationOrder order);

    /**
     * 查询指定管理员相关的关系
     */
    QueryManagerRelationResult queryManagerRelation(QueryManagerRelationOrder order);

    /**
     * 查询关系
     */
    QueryRelationResult queryRelation(QueryRelationOrder order);
}
