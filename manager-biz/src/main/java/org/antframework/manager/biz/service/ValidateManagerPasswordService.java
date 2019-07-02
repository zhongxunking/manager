/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2017-09-20 13:47 创建
 */
package org.antframework.manager.biz.service;

import lombok.AllArgsConstructor;
import org.antframework.common.util.facade.BizException;
import org.antframework.common.util.facade.CommonResultCode;
import org.antframework.common.util.facade.EmptyResult;
import org.antframework.common.util.facade.Status;
import org.antframework.manager.biz.util.SecurityUtils;
import org.antframework.manager.dal.dao.ManagerDao;
import org.antframework.manager.dal.entity.Manager;
import org.antframework.manager.facade.order.ValidateManagerPasswordOrder;
import org.bekit.service.annotation.service.Service;
import org.bekit.service.annotation.service.ServiceExecute;
import org.bekit.service.engine.ServiceContext;

import java.util.Objects;

/**
 * 校验管理员密码服务
 */
@Service
@AllArgsConstructor
public class ValidateManagerPasswordService {
    // 管理员dao
    private final ManagerDao managerDao;

    @ServiceExecute
    public void execute(ServiceContext<ValidateManagerPasswordOrder, EmptyResult> context) {
        ValidateManagerPasswordOrder order = context.getOrder();

        Manager manager = managerDao.findByManagerId(order.getManagerId());
        if (manager == null) {
            throw new BizException(Status.FAIL, CommonResultCode.INVALID_PARAMETER.getCode(), String.format("管理员[%s]不存在或密码不正确", order.getManagerId()));
        }
        if (!Objects.equals(SecurityUtils.digest(order.getPassword()), manager.getPassword())) {
            throw new BizException(Status.FAIL, CommonResultCode.INVALID_PARAMETER.getCode(), String.format("管理员[%s]不存在或密码不正确", order.getManagerId()));
        }
    }
}
