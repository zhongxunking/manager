/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2018-08-04 00:57 创建
 */
package org.antframework.manager.facade.order;

import lombok.Getter;
import lombok.Setter;
import org.antframework.common.util.facade.AbstractOrder;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 查找管理员order
 */
@Getter
@Setter
public class FindManagerOrder extends AbstractOrder {
    // 管理员id
    @NotBlank
    private String managerId;
}
