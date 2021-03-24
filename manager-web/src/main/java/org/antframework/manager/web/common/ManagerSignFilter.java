/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2019-06-30 18:08 创建
 */
package org.antframework.manager.web.common;

import lombok.AllArgsConstructor;
import org.antframework.common.util.facade.CommonResultCode;
import org.antframework.common.util.facade.Status;
import org.antframework.manager.biz.util.Managers;
import org.antframework.manager.biz.util.SecurityUtils;
import org.antframework.manager.facade.info.ManagerInfo;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;

/**
 * 管理员验签Filter
 */
@AllArgsConstructor
public class ManagerSignFilter implements Filter {
    // 签名请求最大存活时长（单位：毫秒）
    private final long maxSurvivalTime;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String managerId = request.getHeader("manager-managerId");
        String requestTime = request.getHeader("manager-requestTime");
        String sign = request.getHeader("manager-sign");

        if (managerId == null || requestTime == null || sign == null) {
            chain.doFilter(req, res);
        } else {
            ManagerInfo manager = validateSign(managerId, requestTime, sign, request, response);
            if (manager != null) {
                CurrentManagerAccessor.setTransientCurrentManager(manager);
                try {
                    chain.doFilter(req, res);
                } finally {
                    CurrentManagerAccessor.setTransientCurrentManager(null);
                }
            }
        }
    }

    // 验证签名
    private ManagerInfo validateSign(String managerId, String requestTime, String sign, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (isExpired(requestTime)) {
            write(response, Status.FAIL, CommonResultCode.INVALID_PARAMETER.getCode(), "请求已过期");
            return null;
        }

        ManagerInfo manager = Managers.findManager(managerId);
        if (manager == null) {
            write(response, Status.FAIL, CommonResultCode.INVALID_PARAMETER.getCode(), String.format("管理员[%s]不存在", managerId));
            return null;
        }
        if (manager.getSecretKey() == null) {
            write(response, Status.FAIL, CommonResultCode.INVALID_PARAMETER.getCode(), String.format("管理员[%s]未设置密钥", managerId));
            return null;
        }
        String correctSign = generateSign(request, requestTime, manager.getSecretKey());
        if (!Objects.equals(sign, correctSign)) {
            write(response, Status.FAIL, CommonResultCode.INVALID_PARAMETER.getCode(), "签名不正确");
            return null;
        }
        return manager;
    }

    /// 是否过期
    private boolean isExpired(String requestTime) {
        long time = Long.parseLong(requestTime);
        long currentTime = System.currentTimeMillis();
        return time >= currentTime - maxSurvivalTime && time <= currentTime + maxSurvivalTime;
    }

    // 生成签名
    private String generateSign(HttpServletRequest request, String requestTime, String secretKey) {
        String parameters = request.getParameterMap().entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .map(entry -> entry.getKey() + "=" + Arrays.stream(entry.getValue()).reduce((left, right) -> left + "," + right).orElse(""))
                .reduce((left, right) -> left + "&" + right)
                .orElse("");
        if (parameters.length() > 0) {
            parameters += '&';
        }
        parameters += "requestTime=" + requestTime;
        parameters += "&secretKey=" + secretKey;
        return SecurityUtils.digest(parameters);
    }

    // 设置返回结果
    private void write(HttpServletResponse response, Status status, String code, String message) throws IOException {
        String result = String.format("{\"status\":\"%s\",\"code\":\"%s\",\"message\":\"%s\",\"success\":%b}",
                status,
                code,
                message,
                status == Status.SUCCESS);
        response.setCharacterEncoding("utf-8");
        response.getWriter().append(result);
    }
}
