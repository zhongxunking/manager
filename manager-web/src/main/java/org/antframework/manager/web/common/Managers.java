/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2018-02-07 10:43 创建
 */
package org.antframework.manager.web.common;

import org.antframework.boot.core.Contexts;
import org.antframework.common.util.facade.BizException;
import org.antframework.common.util.facade.EmptyResult;
import org.antframework.common.util.facade.Status;
import org.antframework.manager.facade.api.RelationService;
import org.antframework.manager.facade.order.DeleteRelationOrder;
import org.antframework.manager.facade.order.QueryManagerRelationOrder;
import org.antframework.manager.facade.result.QueryManagerRelationResult;

/**
 * manager工具类
 */
public final class Managers {
    // 关系服务
    private static final RelationService relationService = Contexts.getApplicationContext().getBean(RelationService.class);

    /**
     * 删除指定目标的所有关系
     *
     * @param targetId 目标id
     */
    public static void deleteAllRelationsByTarget(String targetId) {
        DeleteRelationOrder deleteRelationOrder = new DeleteRelationOrder();
        deleteRelationOrder.setTargetId(targetId);

        EmptyResult result = relationService.deleteRelation(deleteRelationOrder);
        if (!result.isSuccess()) {
            throw new BizException(result.getStatus(), result.getCode(), result.getMessage());
        }
    }

    /**
     * 查询与当前管理员相关的关系
     *
     * @param pageNo   页码（必填）
     * @param pageSize 每页大小（必填）
     * @param targetId 目标id（选填）
     * @return 查询结果
     */
    public static QueryManagerRelationResult queryManagerRelation(int pageNo, int pageSize, String targetId) {
        QueryManagerRelationOrder order = new QueryManagerRelationOrder();
        order.setPageNo(pageNo);
        order.setPageSize(pageSize);
        order.setManagerId(ManagerAssert.currentManager().getManagerId());
        order.setTargetId(targetId);

        QueryManagerRelationResult result = relationService.queryManagerRelation(order);
        if (!result.isSuccess()) {
            throw new BizException(Status.FAIL, result.getCode(), result.getMessage());
        }
        return result;
    }
}
