/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2017-06-22 13:06 创建
 */
package org.antframework.manager.web;

import org.antframework.manager.web.common.GlobalExceptionHandler;
import org.antframework.manager.web.common.SessionAccessorFilter;
import org.antframework.manager.web.controller.ManagerInitController;
import org.antframework.manager.web.controller.ManagerMainController;
import org.antframework.manager.web.controller.ManagerManageController;
import org.antframework.manager.web.controller.RelationManageController;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;

/**
 * web层配置
 */
@Configuration
@Import({GlobalExceptionHandler.class,
        ManagerInitController.class,
        ManagerMainController.class,
        ManagerManageController.class,
        RelationManageController.class})
public class WebConfiguration {

    /**
     * session访问器设置filter
     */
    @Bean
    FilterRegistrationBean managerSessionAccessorFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new SessionAccessorFilter());
        registrationBean.setOrder(Ordered.LOWEST_PRECEDENCE - 10);
        registrationBean.addUrlPatterns("/*");

        return registrationBean;
    }
}
