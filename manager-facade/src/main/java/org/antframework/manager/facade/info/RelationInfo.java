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
    // 类型
    private String type;
    // 源
    private String source;
    // 目标
    private String target;
    // 值
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
