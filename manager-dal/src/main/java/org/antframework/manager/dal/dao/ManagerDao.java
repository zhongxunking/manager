/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2018-01-24 09:01 创建
 */
package org.antframework.manager.dal.dao;

import org.antframework.common.util.query.QueryParam;
import org.antframework.manager.dal.entity.Manager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.Collection;

/**
 * 管理员dao
 */
@RepositoryDefinition(domainClass = Manager.class, idClass = Long.class)
public interface ManagerDao {

    void save(Manager manager);

    void delete(Manager manager);

    Manager findLockByManagerId(String managerId);

    Page<Manager> query(Collection<QueryParam> queryParams, Pageable pageable);
}
