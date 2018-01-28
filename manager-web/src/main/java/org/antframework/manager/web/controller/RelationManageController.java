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
import org.antframework.manager.facade.result.QueryManagerRelationResult;
import org.antframework.manager.facade.result.QueryRelationResult;
import org.antframework.manager.web.common.ManagerSessionAccessor;
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
        ManagerSessionAccessor.assertAdmin();
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
        ManagerSessionAccessor.assertAdmin();
        DeleteRelationOrder order = new DeleteRelationOrder();
        order.setManagerId(managerId);
        order.setTargetId(targetId);

        return relationService.deleteRelation(order);
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
        ManagerSessionAccessor.assertAdminOrMyself(managerId);
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
    @RequestMapping("/queryManagerRelation")
    public QueryManagerRelationResult queryManagerRelation(int pageNo, int pageSize, String managerId, String targetId) {
        ManagerSessionAccessor.assertAdminOrMyself(managerId);
        QueryManagerRelationOrder order = new QueryManagerRelationOrder();
        order.setPageNo(pageNo);
        order.setPageSize(pageSize);
        order.setManagerId(managerId);
        order.setTargetId(targetId);

        return relationService.queryManagerRelation(order);
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
    public QueryRelationResult query(int pageNo, int pageSize, String managerId, String targetId) {
        ManagerSessionAccessor.assertAdmin();
        QueryRelationOrder order = new QueryRelationOrder();
        order.setPageNo(pageNo);
        order.setPageSize(pageSize);
        order.setManagerId(managerId);
        order.setTargetId(targetId);

        return relationService.queryRelation(order);
    }
}
