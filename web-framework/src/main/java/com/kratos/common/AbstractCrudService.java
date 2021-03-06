package com.kratos.common;

import com.kratos.common.utils.StringUtils;
import com.kratos.entity.BaseEntity;
import com.kratos.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.util.*;

/**
 * 抽象service提供基本的增删改查操作
 * @author tang he
 * @since 1.0.0
 * @param <T> 增删改查的实体
 */
public abstract class AbstractCrudService<T extends BaseEntity> implements CrudService<T> {
    @Value("${service.showAllEntities}")
    private Boolean showAllEntities;

    /**
     * 获取实体Repository
     * {@link PageRepository} 实现类
     */
    @Autowired
    protected PageRepository<T> pageRepository;

    /**
     * 获取实体Repository
     * @return {@link PageRepository} 实现类
     */
    protected PageRepository<T> getRepository() {
        return this.pageRepository;
    }

    @Override
    @SuppressWarnings("unchecked")
    public PageResult<T> findAll(PageRequest pageRequest, Map<String, String[]> param) {
        Page<T> page = getRepository().findAll(getSpecification(param), pageRequest);
        return new PageResult<>(page);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> findAll(Map<String, String[]> param) {
        return getRepository().findAll(getSpecification(param));
    }

    @Override
    public T findOne(String id) {
        return getRepository().findOne(id);
    }

    @Override
    public void delete(String id) {
        T t = getRepository().findOne(id);
        t.setLogicallyDeleted(true);
        getRepository().save(t);
    }

    @Override
    public T save(T t) {
        return getRepository().save(t);
    }

    /**
     * 提供重写查询的入口
     * @param param 用户传入的查询条件
     * @return {@link Specification}
     */
    protected SimpleSpecification getSpecification(Map<String, String[]> param) {
        return new SimpleSpecification(param, showAllEntities);
    }

    /**
     * 提供重写查询的入口
     * @param param 用户传入的查询条件
     * @return {@link Specification}
     */
    protected SimpleSpecification getSpecificationForAllEntities(Map<String, String[]> param) {
        return new SimpleSpecification(param, true);
    }

    public void sort(List<T> ts) {
        T t;
        for (int i = 0; i < ts.size(); i++) {
            if(StringUtils.isBlank(ts.get(i).getId())) {
                throw new BusinessException("参数不正确，缺失主键");
            }
            t = getRepository().findOne(ts.get(i).getId());
            t.setSortNumber(i);
            getRepository().save(t);
        }
    }
}
