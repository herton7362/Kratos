package com.kratos.common;

import com.kratos.entity.BaseEntity;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Date;

public class ExtendedJpaRepository<T extends BaseEntity> extends SimpleJpaRepository<T, String> implements PageRepository<T> {
    /**
     * Creates a new {@link ExtendedJpaRepository} for the given {@link JpaEntityInformation} and {@link EntityManager}.
     *
     * @param entityInformation must not be {@literal null}.
     * @param entityManager must not be {@literal null}.
     */
    public ExtendedJpaRepository(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }

    @Override
    @Transactional
    public <S extends T> S save(S entity) {
        if (entity.getId() == null) {
            entity.setCreatedDate(new Date().getTime());
        }
        if(entity.getLogicallyDeleted() == null) {
            entity.setLogicallyDeleted(false);
        }
        entity.setUpdatedDate(new Date().getTime());
        return super.save(entity);
    }
}
