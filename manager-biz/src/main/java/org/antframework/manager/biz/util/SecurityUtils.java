/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2017-09-22 21:13 创建
 */
package org.antframework.manager.biz.util;

import org.antframework.common.util.digest.Digests;

import java.util.UUID;

/**
 * 密码工具类
 */
public final class SecurityUtils {
    /**
     * 加密
     *
     * @param str 待加密的字符串
     */
    public static String digest(String str) {
        return Digests.md5DigestAsHex(str);
    }

    /**
     * 获取新的随机码
     */
    public static String newRandomCode() {
        String key = UUID.randomUUID().toString() + System.nanoTime();
        key = digest(key);
        return key;
    }
}
