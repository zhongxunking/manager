/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2017-09-20 10:53 创建
 */
package org.antframework.manager.facade.order;

import lombok.Getter;
import lombok.Setter;
import org.antframework.common.util.facade.AbstractOrder;

import javax.validation.constraints.NotBlank;

/**
 * 删除管理员order
 */
@Getter
@Setter
public class DeleteManagerOrder extends AbstractOrder {
    // 管理员id
    @NotBlank
    private String managerId;
}
