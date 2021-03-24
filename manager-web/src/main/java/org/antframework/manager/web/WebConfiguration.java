/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2017-06-22 13:06 创建
 */
package org.antframework.manager.web;

import org.antframework.manager.web.common.GlobalExceptionHandler;
import org.antframework.manager.web.common.ManagerSignFilter;
import org.antframework.manager.web.controller.ManagerInitController;
import org.antframework.manager.web.controller.ManagerMainController;
import org.antframework.manager.web.controller.ManagerManageController;
import org.antframework.manager.web.controller.RelationManageController;
import org.antframework.manager.web.extension.CurrentManagerService;
import org.antframework.manager.web.extension.session.ManagerSessionAccessorFilter;
import org.antframework.manager.web.extension.session.SessionCurrentManagerService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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
     * 管理员Session访问器filter
     */
    @Bean
    public FilterRegistrationBean managerSessionAccessorFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean<>(new ManagerSessionAccessorFilter());
        registrationBean.setOrder(Ordered.LOWEST_PRECEDENCE - 200);
        registrationBean.addUrlPatterns("/*");

        return registrationBean;
    }

    /**
     * 管理员验签filter
     */
    @Bean
    public FilterRegistrationBean managerSignFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean<>(new ManagerSignFilter(120000));
        registrationBean.setOrder(Ordered.LOWEST_PRECEDENCE - 100);
        registrationBean.addUrlPatterns("/*");

        return registrationBean;
    }

    @Configuration
    @ConditionalOnMissingBean(CurrentManagerService.class)
    @Import(SessionCurrentManagerService.class)
    public static class CurrentManagerConfiguration {
    }
}
