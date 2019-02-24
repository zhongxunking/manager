/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2018-11-07 22:31 创建
 */
package org.antframework.manager.facade.order;

import lombok.Getter;
import lombok.Setter;
import org.antframework.common.util.facade.AbstractQueryOrder;
import org.antframework.common.util.query.annotation.operator.QueryEQ;
import org.antframework.common.util.query.annotation.operator.QueryLike;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 查询指定源的关系order
 */
@Getter
@Setter
public class QuerySourceRelationsOrder extends AbstractQueryOrder {
    // 类型
    @QueryEQ
    @NotBlank
    private String type;
    // 源
    @QueryEQ
    @NotBlank
    private String source;
    // 目标
    @QueryLike
    private String target;
}
