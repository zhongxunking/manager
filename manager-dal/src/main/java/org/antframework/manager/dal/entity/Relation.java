/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2018-01-25 21:09 创建
 */
package org.antframework.manager.dal.entity;

import org.antframework.boot.jpa.AbstractEntity;

import javax.persistence.*;

/**
 * 关系
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "uk_type_source_target", columnNames = {"type", "source", "target"}),
        indexes = {@Index(name = "idx_type_source", columnList = "type,source"),
                @Index(name = "idx_type_target", columnList = "type,target")})
public class Relation extends AbstractEntity {
    // 类型
    @Column(length = 64)
    private String type;

    // 源
    @Column(length = 128)
    private String source;

    // 目标
    @Column(length = 128)
    private String target;

    // 值
    @Column(length = 2048)
    private String value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
