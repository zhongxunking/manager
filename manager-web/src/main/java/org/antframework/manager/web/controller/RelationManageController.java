/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2018-01-28 23:27 创建
 */
package org.antframework.manager.web.controller;

import org.antframework.common.util.facade.EmptyResult;
import org.antframework.manager.facade.api.RelationService;
import org.antframework.manager.facade.order.*;
import org.antframework.manager.facade.result.FindRelationResult;
import org.antframework.manager.facade.result.QueryManagerRelationsResult;
import org.antframework.manager.facade.result.QueryRelationsResult;
import org.antframework.manager.web.common.ManagerAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 关系管理controller
 */
@RestController
@RequestMapping("/manager/relation")
public class RelationManageController {
    @Autowired
    private RelationService relationService;

    /**
     * 新增关系
     *
     * @param managerId 管理员id（必填）
     * @param targetId  目标id（必填）
     * @return 新增结果
     */
    @RequestMapping("/add")
    public EmptyResult add(String managerId, String targetId) {
        ManagerAssert.admin();
        AddRelationOrder order = new AddRelationOrder();
        order.setManagerId(managerId);
        order.setTargetId(targetId);

        return relationService.addRelation(order);
    }

    /**
     * 删除关系
     *
     * @param managerId 管理员id（选填）
     * @param targetId  目标id（选填）
     * @return 删除结果
     */
    @RequestMapping("/delete")
    public EmptyResult delete(String managerId, String targetId) {
        ManagerAssert.admin();
        DeleteRelationsOrder order = new DeleteRelationsOrder();
        order.setManagerId(managerId);
        order.setTargetId(targetId);

        return relationService.deleteRelations(order);
    }

    /**
     * 查找关系
     *
     * @param managerId 管理员id（必填）
     * @param targetId  目标id（必填）
     * @return 查找结果
     */
    @RequestMapping("/find")
    public FindRelationResult find(String managerId, String targetId) {
        ManagerAssert.adminOrMyself(managerId);
        FindRelationOrder order = new FindRelationOrder();
        order.setManagerId(managerId);
        order.setTargetId(targetId);

        return relationService.findRelation(order);
    }

    /**
     * 查询与指定管理员相关的关系
     *
     * @param pageNo    页码（必填）
     * @param pageSize  每页大小（必填）
     * @param managerId 管理员id（必填）
     * @param targetId  目标id（选填）
     * @return 查询结果
     */
    @RequestMapping("/queryManagerRelations")
    public QueryManagerRelationsResult queryManagerRelations(int pageNo, int pageSize, String managerId, String targetId) {
        ManagerAssert.adminOrMyself(managerId);
        QueryManagerRelationsOrder order = new QueryManagerRelationsOrder();
        order.setPageNo(pageNo);
        order.setPageSize(pageSize);
        order.setManagerId(managerId);
        order.setTargetId(targetId);

        return relationService.queryManagerRelations(order);
    }

    /**
     * 查询关系
     *
     * @param pageNo    页码（必填）
     * @param pageSize  每页大小（必填）
     * @param managerId 管理员id（选填）
     * @param targetId  目标id（选填）
     * @return 查询结果
     */
    @RequestMapping("/query")
    public QueryRelationsResult query(int pageNo, int pageSize, String managerId, String targetId) {
        ManagerAssert.admin();
        QueryRelationsOrder order = new QueryRelationsOrder();
        order.setPageNo(pageNo);
        order.setPageSize(pageSize);
        order.setManagerId(managerId);
        order.setTargetId(targetId);

        return relationService.queryRelations(order);
    }
}
