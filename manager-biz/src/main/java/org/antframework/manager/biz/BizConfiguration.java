/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2017-06-22 13:00 创建
 */
package org.antframework.manager.biz;

import org.antframework.manager.biz.provider.ManagerServiceProvider;
import org.antframework.manager.biz.provider.RelationServiceProvider;
import org.antframework.manager.biz.service.*;
import org.antframework.manager.facade.api.ManagerService;
import org.antframework.manager.facade.api.RelationService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * biz层配置
 */
@Configuration
@Import({AddManagerService.class,
        AddOrModifyRelationService.class,
        DeleteManagerService.class,
        DeleteRelationsService.class,
        FindManagerService.class,
        FindRelationService.class,
        ModifyManagerNameService.class,
        ModifyManagerPasswordService.class,
        ModifyManagerSecretKeyService.class,
        ModifyManagerTypeService.class,
        ValidateManagerPasswordService.class})
public class BizConfiguration {
    /**
     * ManagerService配置
     */
    @Configuration
    @ConditionalOnMissingBean(ManagerService.class)
    @Import(ManagerServiceProvider.class)
    public static class ManagerServiceConfiguration {
    }

    /**
     * RelationService配置
     */
    @Configuration
    @ConditionalOnMissingBean(RelationService.class)
    @Import(RelationServiceProvider.class)
    public static class RelationServiceConfiguration {
    }
}
