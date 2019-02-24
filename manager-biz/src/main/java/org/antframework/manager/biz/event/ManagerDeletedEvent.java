/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2018-11-10 15:55 创建
 */
package org.antframework.manager.biz.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.antframework.manager.facade.info.ManagerInfo;

/**
 * 删除管理员事件
 */
@AllArgsConstructor
@Getter
public class ManagerDeletedEvent {
    // 被删除的管理员
    private final ManagerInfo manager;
}
