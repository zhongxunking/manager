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
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * biz层配置
 */
@Configuration
@Import({ManagerServiceProvider.class,
        RelationServiceProvider.class,
        AddManagerService.class,
        AddRelationService.class,
        DeleteManagerService.class,
        DeleteRelationsService.class,
        FindRelationService.class,
        ManagerLoginService.class,
        ModifyManagerNameService.class,
        ModifyManagerPasswordService.class,
        ModifyManagerTypeService.class,
        QueryManagerRelationsService.class})
public class BizConfiguration {
}
