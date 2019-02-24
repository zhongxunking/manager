/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2018-01-25 23:50 创建
 */
package org.antframework.manager.facade.info;

import lombok.Getter;
import lombok.Setter;
import org.antframework.common.util.facade.AbstractInfo;

/**
 * 关系info
 */
@Getter
@Setter
public class RelationInfo extends AbstractInfo {
    // 类型
    private String type;
    // 源
    private String source;
    // 目标
    private String target;
    // 值
    private String value;
}
