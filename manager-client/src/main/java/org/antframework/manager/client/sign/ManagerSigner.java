/* 
 * 作者：钟勋 (email:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2021-03-25 11:58 创建
 */
package org.antframework.manager.client.sign;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.http.HttpMessage;
import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * 管理员签名器
 */
@AllArgsConstructor
@Getter
public class ManagerSigner {
    // 管理员id
    private final String managerId;
    // 密钥
    private final String secretKey;

    /**
     * 签名
     *
     * @param httpMessage http请求
     * @param params      参数
     */
    public void sign(HttpMessage httpMessage, Iterable<? extends NameValuePair> params) {
        HashMap<String, Object> parameterMap = new HashMap<>();
        for (NameValuePair param : params) {
            Object value = parameterMap.get(param.getName());
            if (value == null) {
                value = param.getValue();
            } else {
                if (value instanceof Collection) {
                    ((Collection<Object>) value).add(param.getValue());
                } else {
                    List<Object> temp = new ArrayList<>();
                    temp.add(value);
                    temp.add(param.getValue());
                    value = temp;
                }
            }
            parameterMap.put(param.getName(), value);
        }
        long requestTime = System.currentTimeMillis();
        String sign = ManagerSigns.generateSign(parameterMap, requestTime, secretKey);
        httpMessage.addHeader("manager-managerId", managerId);
        httpMessage.addHeader("manager-requestTime", Long.toString(requestTime));
        httpMessage.addHeader("manager-sign", sign);
    }
}
