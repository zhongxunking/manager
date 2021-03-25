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
     * @param parameters  参数
     */
    public void sign(HttpMessage httpMessage, Iterable<? extends NameValuePair> parameters) {
        HashMap<String, Object> parameterMap = new HashMap<>();
        for (NameValuePair pair : parameters) {
            Object value = parameterMap.get(pair.getName());
            if (value == null) {
                value = pair.getValue();
            } else {
                if (value instanceof Collection) {
                    ((Collection<Object>) value).add(pair.getValue());
                } else {
                    List<Object> temp = new ArrayList<>();
                    temp.add(value);
                    temp.add(pair.getValue());
                    value = temp;
                }
            }
            parameterMap.put(pair.getName(), value);
        }
        long requestTime = System.currentTimeMillis();
        String sign = ManagerSigns.generateSign(parameterMap, requestTime, secretKey);
        httpMessage.addHeader("manager-managerId", managerId);
        httpMessage.addHeader("manager-requestTime", Long.toString(requestTime));
        httpMessage.addHeader("manager-sign", sign);
    }
}
