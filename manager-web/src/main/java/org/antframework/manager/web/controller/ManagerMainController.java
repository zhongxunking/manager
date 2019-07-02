/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2018-01-28 16:37 创建
 */
package org.antframework.manager.web.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.antframework.common.util.facade.AbstractResult;
import org.antframework.common.util.facade.EmptyResult;
import org.antframework.common.util.facade.FacadeUtils;
import org.antframework.manager.biz.util.Managers;
import org.antframework.manager.biz.util.SecurityUtils;
import org.antframework.manager.facade.api.ManagerService;
import org.antframework.manager.facade.info.ManagerInfo;
import org.antframework.manager.web.common.ManagerSessionAccessor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理员主体controller
 */
@RestController
@RequestMapping("/manager/main")
@AllArgsConstructor
public class ManagerMainController {
    // 管理员服务
    private final ManagerService managerService;

    /**
     * 登录
     *
     * @param managerId 管理员id（必填）
     * @param password  密码（必填）
     * @return 登陆结果
     */
    @RequestMapping("/login")
    public LoginResult login(String managerId, String password) {
        Managers.validateManagerPassword(managerId, password);
        ManagerInfo manager = Managers.findManager(managerId);
        ManagerSessionAccessor.setManager(manager);

        LoginResult result = FacadeUtils.buildSuccess(LoginResult.class);
        result.setManager(manager);
        return result;
    }

    /**
     * 退出登录
     */
    @RequestMapping("/logout")
    public EmptyResult logout() {
        ManagerSessionAccessor.setManager(null);
        return FacadeUtils.buildSuccess(EmptyResult.class);
    }

    /**
     * 获取当前信息
     */
    @RequestMapping("/current")
    public CurrentResult current() {
        CurrentResult result = FacadeUtils.buildSuccess(CurrentResult.class);
        result.setManager(ManagerSessionAccessor.getManager());
        return result;
    }

    /**
     * 获取随机码
     */
    @RequestMapping("/randomCode")
    public RandomCodeResult randomCode() {
        RandomCodeResult result = FacadeUtils.buildSuccess(RandomCodeResult.class);
        result.setRandomCode(SecurityUtils.newRandomCode());
        return result;
    }

    /**
     * 登陆result
     */
    @Getter
    @Setter
    public static class LoginResult extends AbstractResult {
        // 管理员
        private ManagerInfo manager;
    }

    /**
     * 获取当前信息result
     */
    @Getter
    @Setter
    public static class CurrentResult extends AbstractResult {
        // 管理员
        private ManagerInfo manager;
    }

    /**
     * 获取随机码result
     */
    @Getter
    @Setter
    public static class RandomCodeResult extends AbstractResult {
        // 随机码
        private String randomCode;
    }
}
