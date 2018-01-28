/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2018-01-26 09:33 创建
 */
package org.antframework.manager.facade.order;

import org.antframework.common.util.facade.AbstractQueryOrder;
import org.antframework.common.util.query.annotation.operator.QueryEQ;
import org.antframework.common.util.query.annotation.operator.QueryLike;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 查询指定管理员相关的关系order
 */
public class QueryManagerRelationOrder extends AbstractQueryOrder {
    // 管理员id
    @QueryEQ
    @NotBlank
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