/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2017-09-19 16:55 创建
 */
package org.antframework.manager.dal.entity;

import org.antframework.boot.jpa.AbstractEntity;
import org.antframework.manager.facade.enums.ManagerType;

import javax.persistence.*;

/**
 * 管理员
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "uk_managerId", columnNames = "managerId"))
public class Manager extends AbstractEntity {
    // 管理员id
    @Column(length = 128)
    private String managerId;

    // 类型
    @Column(length = 64)
    @Enumerated(EnumType.STRING)
    private ManagerType type;

    // 名称
    @Column
    private String name;

    // 密码
    @Column(length = 64)
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
