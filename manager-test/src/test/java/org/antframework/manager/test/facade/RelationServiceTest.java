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
import org.antframework.manager.facade.order.*;
import org.antframework.manager.facade.result.FindRelationResult;
import org.antframework.manager.facade.result.QueryRelationsResult;
import org.antframework.manager.facade.result.QuerySourceRelationsResult;
import org.antframework.manager.test.AbstractTest;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 关系服务单元测试
 */
@Ignore
public class RelationServiceTest extends AbstractTest {
    @Autowired
    private RelationService relationService;

    @Test
    public void testAddOrModifyRelation() {
        AddOrModifyRelationOrder order = new AddOrModifyRelationOrder();
        order.setType("manager");
        order.setSource("admin");
        order.setTarget("customer");
        order.setValue("001");

        EmptyResult result = relationService.addOrModifyRelation(order);
        assertSuccess(result);

        order = new AddOrModifyRelationOrder();
        order.setType("manager");
        order.setSource("admin");
        order.setTarget("account");
        order.setValue("002");

        result = relationService.addOrModifyRelation(order);
        assertSuccess(result);
    }

    @Test
    public void testDeleteRelations() {
        DeleteRelationsOrder order = new DeleteRelationsOrder();
        order.setType("manager");
        order.setSource("admin");
        order.setTarget(null);

        EmptyResult result = relationService.deleteRelations(order);
        assertSuccess(result);
    }

    @Test
    public void testFindRelation() {
        FindRelationOrder order = new FindRelationOrder();
        order.setType("manager");
        order.setSource("admin");
        order.setTarget("customer");

        FindRelationResult result = relationService.findRelation(order);
        assertSuccess(result);
    }

    @Test
    public void testQuerySourceRelations() {
        QuerySourceRelationsOrder order = new QuerySourceRelationsOrder();
        order.setPageNo(1);
        order.setPageSize(10);
        order.setType("manager");
        order.setSource("admin");

        QuerySourceRelationsResult result = relationService.querySourceRelations(order);
        assertSuccess(result);
    }

    @Test
    public void testQueryRelations() {
        QueryRelationsOrder order = new QueryRelationsOrder();
        order.setPageNo(1);
        order.setPageSize(10);
        order.setType("manager");

        QueryRelationsResult result = relationService.queryRelations(order);
        assertSuccess(result);
    }
}
