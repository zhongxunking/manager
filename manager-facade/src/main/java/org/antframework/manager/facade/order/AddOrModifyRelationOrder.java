/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2018-01-25 21:17 创建
 */
package org.antframework.manager.facade.order;

import lombok.Getter;
import lombok.Setter;
import org.antframework.common.util.facade.AbstractOrder;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 新增或修改关系order
 */
@Getter
@Setter
public class AddOrModifyRelationOrder extends AbstractOrder {
    // 类型
    @NotBlank
    private String type;
    // 源
    @NotBlank
    private String source;
    // 目标
    @NotBlank
    private String target;
    // 值
    private String value;
}
