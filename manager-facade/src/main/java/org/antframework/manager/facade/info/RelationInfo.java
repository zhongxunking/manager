/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2018-01-25 23:50 创建
 */
package org.antframework.manager.facade.info;

import org.antframework.common.util.facade.AbstractInfo;

/**
 * 关系info
 */
public class RelationInfo extends AbstractInfo {
    // 管理员id
    private String managerId;
    // 目标id
    private String targetId;

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }
}
