/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2018-01-26 10:10 创建
 */
package org.antframework.manager.facade.order;

import org.antframework.common.util.facade.AbstractQueryOrder;
import org.antframework.common.util.query.annotation.operator.QueryLike;

/**
 * 查询关系order
 */
public class QueryRelationsOrder extends AbstractQueryOrder {
    // 管理员id
    @QueryLike
    private String managerId;
    // 目标id
    @QueryLike
    private String targetId;

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
