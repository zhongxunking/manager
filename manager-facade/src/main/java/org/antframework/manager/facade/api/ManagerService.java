/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2018-01-24 09:04 创建
 */
package org.antframework.manager.facade.api;

import org.antframework.common.util.facade.EmptyResult;
import org.antframework.manager.facade.order.*;
import org.antframework.manager.facade.result.FindManagerResult;
import org.antframework.manager.facade.result.QueryManagersResult;

/**
 * 管理员服务
 */
public interface ManagerService {
    /**
     * 添加管理员
     */
    EmptyResult addManager(AddManagerOrder order);

    /**
     * 修改管理员类型
     */
    EmptyResult modifyManagerType(ModifyManagerTypeOrder order);

    /**
     * 修改管理员名称
     */
    EmptyResult modifyManagerName(ModifyManagerNameOrder order);

    /**
     * 修改管理员密码
     */
    EmptyResult modifyManagerPassword(ModifyManagerPasswordOrder order);

    /**
     * 修改管理员的密钥
     */
    EmptyResult modifyManagerSecretKey(ModifyManagerSecretKeyOrder order);

    /**
     * 删除管理员
     */
    EmptyResult deleteManager(DeleteManagerOrder order);

    /**
     * 校验管理员密码
     */
    EmptyResult validateManagerPassword(ValidateManagerPasswordOrder order);

    /**
     * 查找管理员
     */
    FindManagerResult findManager(FindManagerOrder order);

    /**
     * 查询管理员
     */
    QueryManagersResult queryManagers(QueryManagersOrder order);
}
