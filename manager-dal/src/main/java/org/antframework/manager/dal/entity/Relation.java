/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2018-01-25 21:09 创建
 */
package org.antframework.manager.dal.entity;

import org.antframework.boot.jpa.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 关联
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"managerId", "targetId"}))
public class Relation extends AbstractEntity {
    // 管理员id
    @Column(length = 64)
    private String managerId;

    // 目标id
    @Column
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
