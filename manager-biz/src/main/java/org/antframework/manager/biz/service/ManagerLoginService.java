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
import org.antframework.common.util.facade.FacadeUtils;
import org.antframework.common.util.facade.Status;
import org.antframework.manager.biz.util.SecurityUtils;
import org.antframework.manager.dal.dao.ManagerDao;
import org.antframework.manager.dal.entity.Manager;
import org.antframework.manager.facade.info.ManagerInfo;
import org.antframework.manager.facade.order.ManagerLoginOrder;
import org.antframework.manager.facade.result.ManagerLoginResult;
import org.bekit.service.annotation.service.Service;
import org.bekit.service.annotation.service.ServiceExecute;
import org.bekit.service.engine.ServiceContext;
import org.springframework.core.convert.converter.Converter;

import java.util.Objects;

/**
 * 管理员登录服务
 */
@Service
@AllArgsConstructor
public class ManagerLoginService {
    // info转换器
    private static final Converter<Manager, ManagerInfo> INFO_CONVERTER = new FacadeUtils.DefaultConverter<>(ManagerInfo.class);

    // 管理员dao
    private final ManagerDao managerDao;

    @ServiceExecute
    public void execute(ServiceContext<ManagerLoginOrder, ManagerLoginResult> context) {
        ManagerLoginOrder order = context.getOrder();
        ManagerLoginResult result = context.getResult();

        Manager manager = managerDao.findByManagerId(order.getManagerId());
        if (manager == null) {
            throw new BizException(Status.FAIL, CommonResultCode.INVALID_PARAMETER.getCode(), String.format("管理员[%s]不存在或密码不正确", order.getManagerId()));
        }
        if (!Objects.equals(SecurityUtils.digest(order.getPassword()), manager.getPassword())) {
            throw new BizException(Status.FAIL, CommonResultCode.INVALID_PARAMETER.getCode(), String.format("管理员[%s]不存在或密码不正确", order.getManagerId()));
        }
        result.setManager(INFO_CONVERTER.convert(manager));
    }
}
