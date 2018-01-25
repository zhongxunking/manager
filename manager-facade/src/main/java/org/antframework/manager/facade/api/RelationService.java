/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2018-01-25 21:17 创建
 */
package org.antframework.manager.facade.api;

import org.antframework.common.util.facade.EmptyResult;
import org.antframework.manager.facade.order.AddRelationOrder;

/**
 * 关系服务
 */
public interface RelationService {

    /**
     * 新增关系
     */
    EmptyResult addRelation(AddRelationOrder order);
}
