/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2018-01-28 16:37 创建
 */
package org.antframework.manager.web.controller;

import org.antframework.common.util.facade.AbstractResult;
import org.antframework.common.util.facade.CommonResultCode;
import org.antframework.common.util.facade.EmptyResult;
import org.antframework.common.util.facade.Status;
import org.antframework.manager.facade.api.ManagerService;
import org.antframework.manager.facade.info.ManagerInfo;
import org.antframework.manager.facade.order.ManagerLoginOrder;
import org.antframework.manager.facade.result.ManagerLoginResult;
import org.antframework.manager.web.common.ManagerSessionAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理员登录controller
 */
@RestController
@RequestMapping("/manage/manager")
public class ManagerLoginController extends AbstractController {
    @Autowired
    private ManagerService managerService;

    /**
     * 登录
     *
     * @param managerId 管理员id（必填）
     * @param password  密码（必填）
     * @return 登陆结果
     */
    @RequestMapping("/login")
    public ManagerLoginResult login(String managerId, String password) {
        ManagerLoginOrder order = new ManagerLoginOrder();
        order.setManagerId(managerId);
        order.setPassword(password);

        ManagerLoginResult result = managerService.managerLogin(order);
        if (result.isSuccess()) {
            ManagerSessionAccessor.setManager(result.getManagerInfo());
        }
        return result;
    }

    /**
     * 退出登陆
     */
    @RequestMapping("/logout")
    public EmptyResult logout() {
        ManagerSessionAccessor.removeManager();
        return successResult();
    }

    /**
     * 获取当前管理员
     */
    public GetCurrentManagerResult getCurrentManager() {
        GetCurrentManagerResult result = new GetCurrentManagerResult();
        result.setStatus(Status.SUCCESS);
        result.setCode(CommonResultCode.SUCCESS.getCode());
        result.setMessage(CommonResultCode.SUCCESS.getMessage());
        result.setManager(ManagerSessionAccessor.getManager());

        return result;
    }

    /**
     * 获取当前管理员
     */
    public static class GetCurrentManagerResult extends AbstractResult {
        // 管理员
        private ManagerInfo manager;

        public ManagerInfo getManager() {
            return manager;
        }

        public void setManager(ManagerInfo manager) {
            this.manager = manager;
        }
    }
}
