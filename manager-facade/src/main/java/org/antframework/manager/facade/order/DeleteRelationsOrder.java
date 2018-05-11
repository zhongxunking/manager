/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2018-01-25 21:32 创建
 */
package org.antframework.manager.facade.order;

import org.antframework.common.util.facade.AbstractOrder;
import org.antframework.common.util.query.annotation.operator.QueryEQ;

/**
 * 删除关系order
 */
public class DeleteRelationsOrder extends AbstractOrder {
    // 管理员id（null表示删除指定目标的所有关系）
    @QueryEQ
    private String managerId;
    // 目标id（null表示删除指定管理员的所有关系）
    @QueryEQ
    private String targetId;

    @Override
    public void check() {
        super.check();
        if (managerId == null && targetId == null) {
            throw new IllegalArgumentException("managerId和targetId不能同时为null");
        }
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }
}
