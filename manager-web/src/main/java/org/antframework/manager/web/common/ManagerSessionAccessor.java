/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2018-01-28 14:46 创建
 */
package org.antframework.manager.web.common;

import org.antframework.common.util.facade.BizException;
import org.antframework.common.util.facade.CommonResultCode;
import org.antframework.common.util.facade.Status;
import org.antframework.manager.facade.enums.ManagerType;
import org.antframework.manager.facade.info.ManagerInfo;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpSession;

/**
 * session中管理员访问器
 */
public class ManagerSessionAccessor {
    // session持有器
    private static final ThreadLocal<HttpSession> SESSION_HOLDER = new ThreadLocal<>();
    // 管理员在session中的key
    private static final String KEY_MANAGER = "KEY_MANAGER";

    /**
     * 设置session
     */
    static void setSession(HttpSession session) {
        SESSION_HOLDER.set(session);
    }

    /**
     * 删除session
     */
    static void removeSession() {
        SESSION_HOLDER.remove();
    }

    /**
     * 向session设置管理员
     */
    public static void setManager(ManagerInfo info) {
        SESSION_HOLDER.get().setAttribute(KEY_MANAGER, info);
    }

    /**
     * 从session获取管理员
     */
    public static ManagerInfo getManager() {
        return (ManagerInfo) SESSION_HOLDER.get().getAttribute(KEY_MANAGER);
    }

    /**
     * 从session删除管理员
     */
    public static void removeManager() {
        SESSION_HOLDER.get().removeAttribute(KEY_MANAGER);
    }

    /**
     * 断言当前管理员为超级管理员
     */
    public static void assertAdmin() {
        ManagerInfo manager = getManager();
        if (manager == null) {
            throw new BizException(Status.FAIL, CommonResultCode.ILLEGAL_STATE.getCode(), "管理员未登陆");
        }
        if (manager.getType() != ManagerType.ADMIN) {
            throw new BizException(Status.FAIL, CommonResultCode.ILLEGAL_STATE.getCode(), String.format("管理员[%s]不是超级管理员", manager.getManagerId()));
        }
    }

    /**
     * 断言当前管理员为超级管理员或者为指定的管理员
     *
     * @param managerId 管理员id
     */
    public static void assertAdminOrMyself(String managerId) {
        ManagerInfo manager = getManager();
        if (manager == null) {
            throw new BizException(Status.FAIL, CommonResultCode.ILLEGAL_STATE.getCode(), "管理员未登陆");
        }
        if (manager.getType() != ManagerType.ADMIN && !StringUtils.equals(managerId, manager.getManagerId())) {
            throw new BizException(Status.FAIL, CommonResultCode.ILLEGAL_STATE.getCode(), String.format("管理员[%s]不是超级管理员且不是指定管理员[%s]", manager.getManagerId(), managerId));
        }
    }
}
