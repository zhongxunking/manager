/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2019-06-30 17:11 创建
 */
package org.antframework.manager.facade.order;

import lombok.Getter;
import lombok.Setter;
import org.antframework.common.util.facade.AbstractOrder;
import org.antframework.common.util.tostring.format.Mask;

import javax.validation.constraints.NotBlank;

/**
 * 修改管理员的密钥order
 */
@Getter
@Setter
public class ModifyManagerSecretKeyOrder extends AbstractOrder {
    // 管理员id
    @NotBlank
    private String managerId;
    // 密钥
    @Mask(startSize = 0, endSize = 0)
    private String secretKey;
}
