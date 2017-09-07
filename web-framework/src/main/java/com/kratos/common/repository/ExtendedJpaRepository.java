package com.kratos.common.repository;

import com.kratos.common.entity.BaseEntity;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.util.Date;

public class ExtendedJpaRepository<T extends BaseEntity> extends SimpleJpaRepository<T, String> implements BaseRepository<T> {
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
    @SuppressWarnings("unchecked")
    public BaseEntity save(BaseEntity entity) {
        if (entity.getId() == null) {
            entity.setCreatedDate(new Date().getTime());
        }
        entity.setUpdatedDate(new Date().getTime());
        return super.save((T) entity);
    }
}
