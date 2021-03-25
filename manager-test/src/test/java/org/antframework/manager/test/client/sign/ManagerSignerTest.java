/* 
 * 作者：钟勋 (email:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2021-03-25 18:18 创建
 */
package org.antframework.manager.test.client.sign;

import org.antframework.common.util.facade.FacadeUtils;
import org.antframework.common.util.json.JSON;
import org.antframework.manager.client.sign.ManagerSigner;
import org.antframework.manager.facade.result.FindManagerResult;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理员签名器单元测试
 */
@Ignore
public class ManagerSignerTest {
    // 发送http请求的客户端
    private static final HttpClient HTTP_CLIENT = HttpClients.createDefault();
    // 管理员签名器
    private final ManagerSigner managerSigner = new ManagerSigner("zhangsan", "2f1735dbae27218e14fe98aef89d49a2");

    // 测试签名
    @Test
    public void testSign() throws IOException {
        String resultJson = HTTP_CLIENT.execute(buildRequest(), new BasicResponseHandler());
        FindManagerResult result = JSON.OBJECT_MAPPER.readValue(resultJson, FindManagerResult.class);
        FacadeUtils.assertSuccess(result);
    }

    // 构建请求
    private HttpUriRequest buildRequest() {
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(2000)
                .setSocketTimeout(600000)
                .build();

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("managerId", "zhangsan"));
        params.add(new BasicNameValuePair("a1", "v1"));
        params.add(new BasicNameValuePair("a2", "v1"));
        params.add(new BasicNameValuePair("a2", "v2"));
        params.add(new BasicNameValuePair("a3", "v1"));
        params.add(new BasicNameValuePair("a3", "v2"));
        params.add(new BasicNameValuePair("a3", "v3"));

        HttpPost httpPost = new HttpPost("http://localhost:6200/manager/manage/findManager");
        httpPost.setConfig(config);
        httpPost.setEntity(new UrlEncodedFormEntity(params, Charset.forName("utf-8")));
        managerSigner.sign(httpPost, params);
        return httpPost;
    }
}
