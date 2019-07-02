/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2019-06-30 18:08 创建
 */
package org.antframework.manager.web.common;

import org.antframework.common.util.facade.CommonResultCode;
import org.antframework.common.util.facade.Status;
import org.antframework.manager.biz.util.SecurityUtils;
import org.antframework.manager.facade.info.ManagerInfo;
import org.antframework.manager.web.CurrentManagers;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;

/**
 * 验证签名filter
 */
public class SignFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String managerId = request.getHeader("manager-id");
        String sign = request.getHeader("sign");
        if (managerId == null || sign == null || validateSign(managerId, sign, request, response)) {
            try {
                chain.doFilter(req, res);
            } finally {
                if (managerId != null && sign != null) {
                    ManagerSessionAccessor.setManager(null);
                }
            }
        }
    }

    // 验证签名
    private boolean validateSign(String managerId, String sign, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ManagerInfo manager = CurrentManagers.findManager(managerId);
        if (manager == null) {
            write(response, Status.FAIL, CommonResultCode.INVALID_PARAMETER.getCode(), String.format("管理员[%s]不存在", managerId));
            return false;
        }
        if (manager.getSecretKey() == null) {
            write(response, Status.FAIL, CommonResultCode.INVALID_PARAMETER.getCode(), String.format("管理员[%s]未设置密钥", managerId));
            return false;
        }
        String parameters = request.getParameterMap().entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .map(entry -> entry.getKey() + "=" + Arrays.stream(entry.getValue()).reduce((left, right) -> left + right).orElse(""))
                .reduce((left, right) -> left + "," + right)
                .orElse("");
        parameters += manager.getSecretKey();
        String correctSign = SecurityUtils.digest(parameters);
        if (!Objects.equals(sign, correctSign)) {
            write(response, Status.FAIL, CommonResultCode.INVALID_PARAMETER.getCode(), "签名不正确");
            return false;
        }
        ManagerSessionAccessor.setManager(manager);
        return true;
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
