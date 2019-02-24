/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2018-08-04 00:59 创建
 */
package org.antframework.manager.facade.result;

import lombok.Getter;
import lombok.Setter;
import org.antframework.common.util.facade.AbstractResult;
import org.antframework.manager.facade.info.ManagerInfo;

/**
 * 查找管理员result
 */
@Getter
@Setter
public class FindManagerResult extends AbstractResult {
    // 管理员（null表示不存在该管理员）
    private ManagerInfo manager;
}
