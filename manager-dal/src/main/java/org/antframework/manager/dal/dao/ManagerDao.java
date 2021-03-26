/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2018-01-24 09:01 创建
 */
package org.antframework.manager.dal.dao;

import org.antframework.common.util.query.QueryParam;
import org.antframework.manager.common.CacheConstant;
import org.antframework.manager.dal.entity.Manager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.RepositoryDefinition;

import javax.persistence.LockModeType;
import java.util.Collection;

/**
 * 管理员dao
 */
@RepositoryDefinition(domainClass = Manager.class, idClass = Long.class)
public interface ManagerDao {
    @CacheEvict(cacheNames = CacheConstant.MANAGER_CACHE_NAME, key = "#p0.managerId")
    void save(Manager manager);

    @CacheEvict(cacheNames = CacheConstant.MANAGER_CACHE_NAME, key = "#p0.managerId")
    void delete(Manager manager);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Manager findLockByManagerId(String managerId);

    @Cacheable(cacheNames = CacheConstant.MANAGER_CACHE_NAME, key = "#p0")
    Manager findByManagerId(String managerId);

    Page<Manager> query(Collection<QueryParam> queryParams, Pageable pageable);
}
