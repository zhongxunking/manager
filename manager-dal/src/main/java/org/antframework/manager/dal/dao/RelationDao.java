/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2018-01-25 21:15 创建
 */
package org.antframework.manager.dal.dao;

import org.antframework.manager.dal.entity.Relation;
import org.springframework.data.repository.RepositoryDefinition;

/**
 * 关系dao
 */
@RepositoryDefinition(domainClass = Relation.class, idClass = Long.class)
public interface RelationDao {

    void save(Relation relation);

    Relation findLockByManagerIdAndTargetId(String managerId, String targetId);
}
