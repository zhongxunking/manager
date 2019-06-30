/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2017-09-19 21:35 创建
 */
package org.antframework.manager.facade.info;

import lombok.Getter;
import lombok.Setter;
import org.antframework.common.util.facade.AbstractInfo;
import org.antframework.common.util.tostring.format.Mask;
import org.antframework.manager.facade.enums.ManagerType;

/**
 * 管理员info
 */
@Getter
@Setter
public class ManagerInfo extends AbstractInfo {
    // 管理员id
    private String managerId;
    // 类型
    private ManagerType type;
    // 名称
    private String name;
    // 密钥
    @Mask(startSize = 0, endSize = 0)
    private String secretKey;
}
