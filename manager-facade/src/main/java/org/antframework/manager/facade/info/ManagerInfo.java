/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2017-09-19 21:35 创建
 */
package org.antframework.manager.facade.info;

import org.antframework.common.util.facade.AbstractInfo;
import org.antframework.manager.facade.enums.ManagerType;

/**
 * 管理员info
 */
public class ManagerInfo extends AbstractInfo {
    // 管理员id
    private String managerId;
    // 类型
    private ManagerType type;
    // 名称
    private String name;

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
}
