/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2017-09-20 10:40 创建
 */
package org.antframework.manager.facade.order;

import org.antframework.common.util.facade.AbstractOrder;
import org.antframework.common.util.tostring.format.Mask;
import org.antframework.manager.facade.enums.ManagerType;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 添加管理员order
 */
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

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public ManagerType getType() {
        return type;
    }

    public void setType(ManagerType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
