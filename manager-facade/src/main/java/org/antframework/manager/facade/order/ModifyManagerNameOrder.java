/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2017-09-20 22:34 创建
 */
package org.antframework.manager.facade.order;

import lombok.Getter;
import lombok.Setter;
import org.antframework.common.util.facade.AbstractOrder;

import javax.validation.constraints.NotBlank;

/**
 * 修改管理员名称order
 */
@Getter
@Setter
public class ModifyManagerNameOrder extends AbstractOrder {
    // 管理员id
    @NotBlank
    private String managerId;
    // 新名称
    @NotBlank
    private String newName;
}
