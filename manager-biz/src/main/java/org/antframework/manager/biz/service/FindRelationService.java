/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2018-01-25 23:57 创建
 */
package org.antframework.manager.biz.service;

import org.antframework.common.util.facade.FacadeUtils;
import org.antframework.manager.dal.dao.RelationDao;
import org.antframework.manager.dal.entity.Relation;
import org.antframework.manager.facade.info.RelationInfo;
import org.antframework.manager.facade.order.FindRelationOrder;
import org.antframework.manager.facade.result.FindRelationResult;
import org.bekit.service.annotation.service.Service;
import org.bekit.service.annotation.service.ServiceExecute;
import org.bekit.service.engine.ServiceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

/**
 * 查找关系服务
 */
@Service
public class FindRelationService {
    private static final Converter<Relation, RelationInfo> INFO_CONVERTER = new FacadeUtils.DefaultConverter<>(RelationInfo.class);
    @Autowired
    private RelationDao relationDao;

    @ServiceExecute
    public void execute(ServiceContext<FindRelationOrder, FindRelationResult> context) {
        FindRelationOrder order = context.getOrder();
        FindRelationResult result = context.getResult();

        Relation relation = relationDao.findByTypeAndSourceAndTarget(order.getType(), order.getSource(), order.getTarget());
        if (relation != null) {
            result.setRelation(INFO_CONVERTER.convert(relation));
        }
    }
}
