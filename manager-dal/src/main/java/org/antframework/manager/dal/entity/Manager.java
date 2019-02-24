/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2017-09-19 16:55 创建
 */
package org.antframework.manager.dal.entity;

import lombok.Getter;
import lombok.Setter;
import org.antframework.boot.jpa.AbstractEntity;
import org.antframework.common.util.tostring.format.Mask;
import org.antframework.manager.facade.enums.ManagerType;

import javax.persistence.*;

/**
 * 管理员
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "uk_managerId", columnNames = "managerId"))
@Getter
@Setter
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
    @Mask(allMask = true)
    private String password;
}
