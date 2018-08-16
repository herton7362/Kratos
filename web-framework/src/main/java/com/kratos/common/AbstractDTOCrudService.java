package com.kratos.common;

import com.kratos.common.utils.CacheUtils;
import com.kratos.common.utils.SpringUtils;
import com.kratos.common.utils.StringUtils;
import com.kratos.dto.BaseDTO;
import com.kratos.entity.BaseEntity;
import com.kratos.exceptions.BusinessException;
import com.kratos.module.auth.UserThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 抽象service提供基本的增删改查操作
 * @author tang he
 * @since 1.0.0
 * @param <T> 增删改查的实体
 */
public abstract class AbstractDTOCrudService<D extends BaseDTO<D, T>, T extends BaseEntity> implements DTOCrudService<D, T> {
    protected final CacheUtils cache = CacheUtils.getInstance();
    @Value("${service.showAllEntities}")
    private Boolean showAllEntities;

    /**
     * 获取实体Repository
     * {@link PageRepository} 实现类
     */
    @Autowired
    protected PageRepository<T> pageRepository;

    @Autowired
    protected BaseDTO<D, T> baseDTO;

    /**
     * 获取实体Repository
     * @return {@link PageRepository} 实现类
     */
    protected PageRepository<T> getRepository() {
        return this.pageRepository;
    }

    @Override
    @SuppressWarnings("unchecked")
    public PageResult<D> findAll(PageRequest pageRequest, Map<String, String[]> param) {
        Page<T> page = getRepository().findAll(getSpecification(param), pageRequest);
        PageResult<D> pageResult = new PageResult<>();
        pageResult.setContent(baseDTO.convertFor(page.getContent()));
        pageResult.setTotalElements(page.getTotalElements());
        pageResult.setSize(page.getSize());
        pageResult.setNumber(page.getNumber());
        pageResult.setTotalPages(page.getTotalPages());
        return pageResult;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<D> findAll(Map<String, String[]> param) {
        return baseDTO.convertFor(getRepository().findAll(getSpecification(param)));
    }

    @Override
    @SuppressWarnings("unchecked")
    public D findOne(String id) {
        if(cache.get(id) != null) {
            return (D) cache.get(id);
        }
        return baseDTO.convertFor(getRepository().findOne(id));
    }

    @Override
    public void delete(String id) {
        T t = getRepository().findOne(id);
        t.setLogicallyDeleted(true);
        getRepository().save(t);
        cache.remove(id);
    }

    @Override
    public D save(D d) {
        T t = getRepository().save(d.convert());
        d = d.convertFor(t);
        cache.set(d.getId(), d);
        return d;
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
