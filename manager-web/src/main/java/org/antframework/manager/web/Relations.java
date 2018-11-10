/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2018-11-10 20:57 创建
 */
package org.antframework.manager.web;

import org.antframework.boot.core.Contexts;
import org.antframework.common.util.facade.EmptyResult;
import org.antframework.common.util.facade.FacadeUtils;
import org.antframework.manager.facade.api.RelationService;
import org.antframework.manager.facade.info.RelationInfo;
import org.antframework.manager.facade.order.AddOrModifyRelationOrder;
import org.antframework.manager.facade.order.DeleteRelationsOrder;
import org.antframework.manager.facade.order.FindRelationOrder;
import org.antframework.manager.facade.result.FindRelationResult;

/**
 * 关系工具类
 */
public final class Relations {
    // 关系服务
    private static final RelationService RELATION_SERVICE = Contexts.getApplicationContext().getBean(RelationService.class);

    /**
     * 新增或修改关系
     *
     * @param type   类型
     * @param source 源
     * @param target 目标
     * @param value  值
     */
    public static void addOrModifyRelation(String type, String source, String target, String value) {
        AddOrModifyRelationOrder order = new AddOrModifyRelationOrder();
        order.setType(type);
        order.setSource(source);
        order.setTarget(target);
        order.setValue(value);

        EmptyResult result = RELATION_SERVICE.addOrModifyRelation(order);
        FacadeUtils.assertSuccess(result);
    }

    /**
     * 删除关系
     *
     * @param type   类型
     * @param source 源
     * @param target 目标
     */
    public static void deleteRelations(String type, String source, String target) {
        DeleteRelationsOrder order = new DeleteRelationsOrder();
        order.setType(type);
        order.setSource(source);
        order.setTarget(target);

        EmptyResult result = RELATION_SERVICE.deleteRelations(order);
        FacadeUtils.assertSuccess(result);
    }

    /**
     * 查找关系
     *
     * @param type   类型
     * @param source 源
     * @param target 目标
     * @return 关系
     */
    public static RelationInfo findRelation(String type, String source, String target) {
        FindRelationOrder order = new FindRelationOrder();
        order.setType(type);
        order.setSource(source);
        order.setTarget(target);

        FindRelationResult result = RELATION_SERVICE.findRelation(order);
        FacadeUtils.assertSuccess(result);
        return result.getRelation();
    }
}
