/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2017-09-20 13:43 创建
 */
package org.antframework.manager.facade.order;

import lombok.Getter;
import lombok.Setter;
import org.antframework.common.util.facade.AbstractOrder;
import org.antframework.common.util.tostring.format.Mask;

import javax.validation.constraints.NotBlank;

/**
 * 管理员登陆order
 */
@Getter
@Setter
public class ManagerLoginOrder extends AbstractOrder {
    // 管理员Id
    @NotBlank
    private String managerId;
    // 密码
    @NotBlank
    @Mask(allMask = true)
    private String password;
}
