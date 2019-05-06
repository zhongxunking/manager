/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2018-01-28 14:44 创建
 */
package org.antframework.manager.web.controller;

import lombok.AllArgsConstructor;
import org.antframework.common.util.facade.EmptyResult;
import org.antframework.manager.facade.api.ManagerService;
import org.antframework.manager.facade.enums.ManagerType;
import org.antframework.manager.facade.order.*;
import org.antframework.manager.facade.result.FindManagerResult;
import org.antframework.manager.facade.result.QueryManagersResult;
import org.antframework.manager.web.Managers;
import org.antframework.manager.web.common.ManagerSessionAccessor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理员管理controller
 */
@RestController
@RequestMapping("/manager/manage")
@AllArgsConstructor
public class ManagerManageController {
    // 管理员服务
    private final ManagerService managerService;

    /**
     * 新增管理员
     *
     * @param managerId 管理员id（必填）
     * @param type      类型（必填）
     * @param name      名称（必填）
     * @param password  密码（必填）
     * @return 新增管理员结果
     */
    @RequestMapping("/add")
    public EmptyResult add(String managerId, ManagerType type, String name, String password) {
        Managers.admin();
        AddManagerOrder order = new AddManagerOrder();
        order.setManagerId(managerId);
        order.setType(type);
        order.setName(name);
        order.setPassword(password);

        return managerService.addManager(order);
    }

    /**
     * 修改管理员类型
     *
     * @param managerId 被修改的管理员id（必填）
     * @param newType   新类型（必填）
     * @return 修改结果
     */
    @RequestMapping("/modifyType")
    public EmptyResult modifyType(String managerId, ManagerType newType) {
        Managers.admin();
        ModifyManagerTypeOrder order = new ModifyManagerTypeOrder();
        order.setManagerId(managerId);
        order.setNewType(newType);

        EmptyResult result = managerService.modifyManagerType(order);
        tryRefreshSessionManager(managerId);
        return result;
    }

    /**
     * 修改名称
     *
     * @param managerId 被修改的管理员id（必填）
     * @param newName   新名称（必填）
     * @return 修改结果
     */
    @RequestMapping("/modifyName")
    public EmptyResult modifyName(String managerId, String newName) {
        Managers.adminOrMyself(managerId);
        ModifyManagerNameOrder order = new ModifyManagerNameOrder();
        order.setManagerId(managerId);
        order.setNewName(newName);

        EmptyResult result = managerService.modifyManagerName(order);
        tryRefreshSessionManager(managerId);
        return result;
    }

    /**
     * 修改管理员密码
     *
     * @param managerId   被修改的管理员id（必填）
     * @param newPassword 新密码（必填）
     * @return 修改结果
     */
    @RequestMapping("/modifyPassword")
    public EmptyResult modifyPassword(String managerId, String newPassword) {
        Managers.adminOrMyself(managerId);
        ModifyManagerPasswordOrder order = new ModifyManagerPasswordOrder();
        order.setManagerId(managerId);
        order.setNewPassword(newPassword);

        return managerService.modifyManagerPassword(order);
    }

    /**
     * 删除管理员
     *
     * @param managerId 被删除的管理员id（必填）
     * @return 删除管理员结果
     */
    @RequestMapping("/delete")
    public EmptyResult delete(String managerId) {
        Managers.admin();
        DeleteManagerOrder order = new DeleteManagerOrder();
        order.setManagerId(managerId);

        EmptyResult result = managerService.deleteManager(order);
        tryRefreshSessionManager(managerId);
        return result;
    }

    /**
     * 查找管理员
     *
     * @param managerId 管理员id
     * @return 查找管理员结果
     */
    @RequestMapping("/findManager")
    public FindManagerResult findManager(String managerId) {
        Managers.adminOrMyself(managerId);
        FindManagerOrder order = new FindManagerOrder();
        order.setManagerId(managerId);

        return managerService.findManager(order);
    }

    /**
     * 查询管理员
     *
     * @param pageNo    页码（必填）
     * @param pageSize  每页大小（必填）
     * @param managerId 管理员id（选填）
     * @param type      类型（选填）
     * @param name      名称（选填）
     * @return 查询结果
     */
    @RequestMapping("/query")
    public QueryManagersResult query(int pageNo, int pageSize, String managerId, ManagerType type, String name) {
        Managers.admin();
        QueryManagersOrder order = new QueryManagersOrder();
        order.setPageNo(pageNo);
        order.setPageSize(pageSize);
        order.setManagerId(managerId);
        order.setType(type);
        order.setName(name);

        return managerService.queryManagers(order);
    }

    // 尝试刷新session中的管理员
    private void tryRefreshSessionManager(String managerId) {
        try {
            Managers.myself(managerId);
            // 刷新session中的管理员
            FindManagerOrder order = new FindManagerOrder();
            order.setManagerId(managerId);

            FindManagerResult result = managerService.findManager(order);
            if (result.isSuccess()) {
                ManagerSessionAccessor.setManager(result.getManager());
            }
        } catch (Throwable e) {
            // 忽略
        }
    }
}
