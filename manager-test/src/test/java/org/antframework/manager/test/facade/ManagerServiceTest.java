/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2018-01-26 22:32 创建
 */
package org.antframework.manager.test.facade;

import org.antframework.common.util.facade.EmptyResult;
import org.antframework.manager.facade.api.ManagerService;
import org.antframework.manager.facade.enums.ManagerType;
import org.antframework.manager.facade.order.*;
import org.antframework.manager.facade.result.ManagerLoginResult;
import org.antframework.manager.facade.result.QueryManagerResult;
import org.antframework.manager.test.AbstractTest;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 管理员服务单元测试
 */
@Ignore
public class ManagerServiceTest extends AbstractTest {
    @Autowired
    private ManagerService managerService;

    @Test
    public void testAddManager() {
        AddManagerOrder order = new AddManagerOrder();
        order.setManagerId("admin");
        order.setName("钟勋");
        order.setPassword("123");
        order.setType(ManagerType.ADMIN);

        EmptyResult result = managerService.addManager(order);
        assertSuccess(result);
    }

    @Test
    public void testDeleteManager() {
        DeleteManagerOrder order = new DeleteManagerOrder();
        order.setManagerId("admin");

        EmptyResult result = managerService.deleteManager(order);
        assertSuccess(result);
    }

    @Test
    public void testModifyManagerPassword() {
        ModifyManagerPasswordOrder order = new ModifyManagerPasswordOrder();
        order.setManagerId("admin");
        order.setNewPassword("abc");

        EmptyResult result = managerService.modifyManagerPassword(order);
        assertSuccess(result);
    }

    @Test
    public void testModifyManagerType() {
        ModifyManagerTypeOrder order = new ModifyManagerTypeOrder();
        order.setManagerId("admin");
        order.setNewType(ManagerType.NORMAL);

        EmptyResult result = managerService.modifyManagerType(order);
        assertSuccess(result);
    }

    @Test
    public void testModifyManagerName() {
        ModifyManagerNameOrder order = new ModifyManagerNameOrder();
        order.setManagerId("admin");
        order.setNewName("zhongxun");

        EmptyResult result = managerService.modifyManagerName(order);
        assertSuccess(result);
    }

    @Test
    public void testQueryManager() {
        QueryManagerOrder order = new QueryManagerOrder();
        order.setPageNo(1);
        order.setPageSize(10);

        QueryManagerResult result = managerService.queryManager(order);
        assertSuccess(result);
    }

    @Test
    public void testManagerLogin() {
        ManagerLoginOrder order = new ManagerLoginOrder();
        order.setManagerId("admin");
        order.setPassword("123");

        ManagerLoginResult result = managerService.managerLogin(order);
        assertSuccess(result);
    }
}
