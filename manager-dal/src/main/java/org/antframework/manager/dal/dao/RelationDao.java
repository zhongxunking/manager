/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2018-01-25 21:15 创建
 */
package org.antframework.manager.dal.dao;

import org.antframework.common.util.query.QueryParam;
import org.antframework.manager.common.CacheConstant;
import org.antframework.manager.dal.entity.Relation;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.RepositoryDefinition;

import javax.persistence.LockModeType;
import java.util.Collection;
import java.util.List;

/**
 * 关系dao
 */
@RepositoryDefinition(domainClass = Relation.class, idClass = Long.class)
public interface RelationDao {
    @CacheEvict(cacheNames = CacheConstant.RELATION_CACHE_NAME, key = "#p0.type + ',' + #p0.source + ',' + #p0.target")
    void save(Relation relation);

    @CacheEvict(cacheNames = CacheConstant.RELATION_CACHE_NAME, key = "#p0.type + ',' + #p0.source + ',' + #p0.target")
    void delete(Relation relation);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Relation findLockByTypeAndSourceAndTarget(String type, String source, String target);

    @Cacheable(cacheNames = CacheConstant.RELATION_CACHE_NAME, key = "#p0 + ',' + #p1 + ',' + #p2")
    Relation findByTypeAndSourceAndTarget(String type, String source, String target);

    List<Relation> query(Collection<QueryParam> queryParams);

    Page<Relation> query(Collection<QueryParam> queryParams, Pageable pageable);
}
