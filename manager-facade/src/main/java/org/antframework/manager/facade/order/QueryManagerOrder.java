/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2017-09-20 11:14 创建
 */
package org.antframework.manager.facade.order;

import org.antframework.common.util.facade.AbstractQueryOrder;
import org.antframework.common.util.query.annotation.operator.QueryEQ;
import org.antframework.common.util.query.annotation.operator.QueryLike;
import org.antframework.manager.facade.enums.ManagerType;

/**
 * 查询管理员order
 */
public class QueryManagerOrder extends AbstractQueryOrder {
    // 管理员id
    @QueryLike
    private String managerId;
    // 类型
    @QueryEQ
    private ManagerType type;
    // 名称
    @QueryLike
    private String name;

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public ManagerType getType() {
        return type;
    }

    public void setType(ManagerType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
