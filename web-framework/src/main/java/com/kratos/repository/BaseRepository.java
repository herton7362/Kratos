package com.kratos.repository;

import com.kratos.entity.BaseEntity;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

/**
 * A base repository interface declaring a custom method shared amongst all repositories.
 *
 * @author tang he
 * @see ExtendedJpaRepository
 */
@NoRepositoryBean
interface BaseRepository<T extends BaseEntity> extends Repository<T, String> {
    T save(T s);
}
