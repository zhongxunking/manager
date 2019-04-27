/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2017-09-20 10:40 创建
 */
package org.antframework.manager.facade.order;

import lombok.Getter;
import lombok.Setter;
import org.antframework.common.util.facade.AbstractOrder;
import org.antframework.common.util.tostring.format.Mask;
import org.antframework.manager.facade.enums.ManagerType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 添加管理员order
 */
@Getter
@Setter
public class AddManagerOrder extends AbstractOrder {
    // 管理员id
    @NotBlank
    private String managerId;
    // 类型
    @NotNull
    private ManagerType type;
    // 名称
    @NotBlank
    private String name;
    // 密码
    @NotBlank
    @Mask(allMask = true)
    private String password;
}
