/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2018-01-31 00:28 创建
 */
package org.antframework.manager.web.controller;

import org.antframework.common.util.facade.BizException;
import org.antframework.common.util.facade.CommonResultCode;
import org.antframework.common.util.facade.EmptyResult;
import org.antframework.common.util.facade.Status;
import org.antframework.manager.facade.api.ManagerService;
import org.antframework.manager.facade.order.AddManagerOrder;
import org.antframework.manager.facade.order.QueryManagerOrder;
import org.antframework.manager.facade.result.QueryManagerResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理员初始化controller
 */
@RestController
@RequestMapping("/manager/init")
public class ManagerInitController {
    @Autowired
    private ManagerService managerService;

    /**
     * 是否能初始化超级管理员
     */
    @RequestMapping("/canInitAdmin")
    public EmptyResult canInitAdmin() {
        assertCanInitAdmin();

        EmptyResult result = new EmptyResult();
        result.setStatus(Status.SUCCESS);
        result.setCode(CommonResultCode.SUCCESS.getCode());
        result.setMessage(CommonResultCode.SUCCESS.getMessage());
        return result;
    }

    /**
     * 初始化超级管理员
     *
     * @param managerId 管理员id（必填）
     * @param name      名称（必填）
     * @param password  密码（必填）
     * @return 初始化结果
     */
    @RequestMapping("/initAdmin")
    public EmptyResult initAdmin(String managerId, String name, String password) {
        assertCanInitAdmin();
        AddManagerOrder order = new AddManagerOrder();
        order.setManagerId(managerId);
        order.setName(name);
        order.setPassword(password);

        return managerService.addManager(order);
    }

    // 断言能初始化超级管理员
    private void assertCanInitAdmin() {
        QueryManagerOrder order = new QueryManagerOrder();
        order.setPageNo(1);
        order.setPageSize(1);
        QueryManagerResult result = managerService.queryManager(order);
        if (!result.isSuccess()) {
            throw new BizException(Status.FAIL, result.getCode(), result.getMessage());
        }
        if (result.getTotalCount() > 0) {
            throw new BizException(Status.FAIL, CommonResultCode.ILLEGAL_STATE.getCode(), "已存在管理员，不能初始化超级管理员");
        }
    }
}
