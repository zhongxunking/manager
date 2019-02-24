/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2018-01-25 23:49 创建
 */
package org.antframework.manager.facade.order;

import lombok.Getter;
import lombok.Setter;
import org.antframework.common.util.facade.AbstractOrder;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 查找关系order
 */
@Getter
@Setter
public class FindRelationOrder extends AbstractOrder {
    // 类型
    @NotBlank
    private String type;
    // 源
    @NotBlank
    private String source;
    // 目标
    @NotBlank
    private String target;
}
