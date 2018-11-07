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
import org.antframework.manager.facade.result.QueryRelationsResult;
import org.antframework.manager.facade.result.QuerySourceRelationsResult;
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
     * 新增或修改关系
     *
     * @param type   类型（必填）
     * @param source 源（必填）
     * @param target 目标（必填）
     * @param value  值（选填）
     * @return 新增或修改结果
     */
    @RequestMapping("/addOrModify")
    public EmptyResult addOrModify(String type, String source, String target, String value) {
        ManagerAssert.admin();
        AddOrModifyRelationOrder order = new AddOrModifyRelationOrder();
        order.setType(type);
        order.setSource(source);
        order.setTarget(target);
        order.setValue(value);

        return relationService.addOrModifyRelation(order);
    }

    /**
     * 删除关系
     *
     * @param type   类型（必填）
     * @param source 源（不填表示删除指定目标的所有关系）
     * @param target 目标（不填表示删除指定源的所有关系）
     * @return 删除结果
     */
    @RequestMapping("/delete")
    public EmptyResult delete(String type, String source, String target) {
        ManagerAssert.admin();
        DeleteRelationsOrder order = new DeleteRelationsOrder();
        order.setType(type);
        order.setSource(source);
        order.setTarget(target);

        return relationService.deleteRelations(order);
    }

    /**
     * 查找关系
     *
     * @param type   类型（必填）
     * @param source 源（必填）
     * @param target 目标（必填）
     * @return 查找结果
     */
    @RequestMapping("/find")
    public FindRelationResult find(String type, String source, String target) {
        ManagerAssert.admin();
        FindRelationOrder order = new FindRelationOrder();
        order.setType(type);
        order.setSource(source);
        order.setTarget(target);

        return relationService.findRelation(order);
    }

    /**
     * 查询指定源的关系
     *
     * @param pageNo   页码（必填）
     * @param pageSize 每页大小（必填）
     * @param type     类型（必填）
     * @param source   源（必填）
     * @param target   目标（选填）
     * @return 查询结果
     */
    @RequestMapping("querySourceRelations")
    public QuerySourceRelationsResult querySourceRelations(int pageNo, int pageSize, String type, String source, String target) {
        ManagerAssert.admin();
        QuerySourceRelationsOrder order = new QuerySourceRelationsOrder();
        order.setPageNo(pageNo);
        order.setPageSize(pageSize);
        order.setType(type);
        order.setSource(source);
        order.setTarget(target);

        return relationService.querySourceRelations(order);
    }

    /**
     * 查询关系
     *
     * @param pageNo   页码（必填）
     * @param pageSize 每页大小（必填）
     * @param type     类型（必填）
     * @param source   源（选填）
     * @param target   目标（选填）
     * @return 查询结果
     */
    @RequestMapping("/query")
    public QueryRelationsResult query(int pageNo, int pageSize, String type, String source, String target) {
        ManagerAssert.admin();
        QueryRelationsOrder order = new QueryRelationsOrder();
        order.setPageNo(pageNo);
        order.setPageSize(pageSize);
        order.setType(type);
        order.setSource(source);
        order.setTarget(target);

        return relationService.queryRelations(order);
    }
}
