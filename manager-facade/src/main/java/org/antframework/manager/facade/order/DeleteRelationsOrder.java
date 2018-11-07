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
import org.hibernate.validator.constraints.NotBlank;

/**
 * 删除关系order
 */
public class DeleteRelationsOrder extends AbstractOrder {
    // 类型
    @QueryEQ
    @NotBlank
    private String type;
    // 源（null表示删除指定目标的所有关系）
    @QueryEQ
    private String source;
    // 目标（null表示删除指定源的所有关系）
    @QueryEQ
    private String target;

    @Override
    public void check() {
        super.check();
        if (source == null && target == null) {
            throw new IllegalArgumentException("source和target不能同时为null");
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
