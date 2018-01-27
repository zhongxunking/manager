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
import org.antframework.manager.facade.order.AddManagerOrder;
import org.antframework.manager.test.AbstractTest;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
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
}
