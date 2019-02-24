/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2017-09-20 13:44 创建
 */
package org.antframework.manager.facade.result;

import lombok.Getter;
import lombok.Setter;
import org.antframework.common.util.facade.AbstractResult;
import org.antframework.manager.facade.info.ManagerInfo;

/**
 * 管理员登陆result
 */
@Getter
@Setter
public class ManagerLoginResult extends AbstractResult {
    // 管理员
    private ManagerInfo manager;
}
