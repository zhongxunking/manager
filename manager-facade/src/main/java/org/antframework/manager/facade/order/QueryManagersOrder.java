/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2017-09-20 11:14 创建
 */
package org.antframework.manager.facade.order;

import lombok.Getter;
import lombok.Setter;
import org.antframework.common.util.facade.AbstractQueryOrder;
import org.antframework.common.util.query.annotation.operator.QueryEQ;
import org.antframework.common.util.query.annotation.operator.QueryLike;
import org.antframework.manager.facade.enums.ManagerType;

/**
 * 查询管理员order
 */
@Getter
@Setter
public class QueryManagersOrder extends AbstractQueryOrder {
    // 管理员id
    @QueryLike
    private String managerId;
    // 类型
    @QueryEQ
    private ManagerType type;
    // 名称
    @QueryLike
    private String name;
}
