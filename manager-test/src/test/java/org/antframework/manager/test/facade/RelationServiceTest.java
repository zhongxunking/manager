/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2018-01-27 21:43 创建
 */
package org.antframework.manager.test.facade;

import org.antframework.common.util.facade.EmptyResult;
import org.antframework.manager.facade.api.RelationService;
import org.antframework.manager.facade.order.AddRelationOrder;
import org.antframework.manager.facade.order.FindRelationOrder;
import org.antframework.manager.facade.order.QueryManagerRelationOrder;
import org.antframework.manager.facade.result.FindRelationResult;
import org.antframework.manager.facade.result.QueryManagerRelationResult;
import org.antframework.manager.test.AbstractTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 关系服务单元测试
 */
public class RelationServiceTest extends AbstractTest {
    @Autowired
    private RelationService relationService;

    @Test
    public void testAddRelation() {
        AddRelationOrder order = new AddRelationOrder();
        order.setManagerId("admin");
        order.setTargetId("uid");

        EmptyResult result = relationService.addRelation(order);
        assertSuccess(result);
    }

    @Test
    public void testFindRelation() {
        FindRelationOrder order = new FindRelationOrder();
        order.setManagerId("admin");
        order.setTargetId("uid");

        FindRelationResult result = relationService.findRelation(order);
        assertSuccess(result);
    }

    @Test
    public void testQueryManagerRelation() {
        QueryManagerRelationOrder order = new QueryManagerRelationOrder();
        order.setPageNo(1);
        order.setPageSize(10);
        order.setManagerId("admin");

        QueryManagerRelationResult result = relationService.queryManagerRelation(order);
        assertSuccess(result);
    }
}
