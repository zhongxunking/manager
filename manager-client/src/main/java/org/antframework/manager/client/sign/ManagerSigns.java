/* 
 * 作者：钟勋 (email:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2021-03-25 13:44 创建
 */
package org.antframework.manager.client.sign;

import org.antframework.common.util.digest.Digests;

import java.util.*;

/**
 * 管理员签名工具
 */
public class ManagerSigns {
    /**
     * 生成签名
     *
     * @param parameters  参数
     * @param requestTime 请求时间
     * @param secretKey   密钥
     * @return 签名
     */
    public static String generateSign(Map<String, Object> parameters, long requestTime, String secretKey) {
        String str = parameters.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .map(entry -> {
                    String value;
                    if (entry.getValue() == null) {
                        value = "";
                    } else if (entry.getValue() instanceof Object[]) {
                        value = Arrays.stream((Object[]) entry.getValue())
                                .filter(Objects::nonNull)
                                .map(Object::toString)
                                .reduce((left, right) -> left + "," + right)
                                .orElse("");
                    } else if (entry.getValue() instanceof Collection) {
                        value = ((Collection<?>) entry.getValue()).stream()
                                .filter(Objects::nonNull)
                                .map(Object::toString)
                                .reduce((left, right) -> left + "," + right)
                                .orElse("");
                    } else {
                        value = entry.getValue().toString();
                    }
                    return entry.getKey() + "=" + value;
                })
                .reduce((left, right) -> left + "&" + right)
                .orElse("");
        if (str.length() > 0) {
            str += "&";
        }
        str += "requestTime=" + requestTime;
        str += "&secretKey=" + secretKey;

        return Digests.md5DigestAsHex(str);
    }
}
