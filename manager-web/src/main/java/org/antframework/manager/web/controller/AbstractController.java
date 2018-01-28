/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2018-01-28 16:44 创建
 */
package org.antframework.manager.web.controller;

import org.antframework.common.util.facade.CommonResultCode;
import org.antframework.common.util.facade.EmptyResult;
import org.antframework.common.util.facade.Status;

/**
 * 抽象controller
 */
public class AbstractController {

    /**
     * 构建成功result
     */
    protected EmptyResult successResult() {
        EmptyResult result = new EmptyResult();
        result.setStatus(Status.SUCCESS);
        result.setCode(CommonResultCode.SUCCESS.getCode());
        result.setMessage(CommonResultCode.SUCCESS.getMessage());

        return result;
    }
}
