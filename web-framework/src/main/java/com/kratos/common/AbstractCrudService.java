package com.kratos.common;

import com.kratos.common.utils.SpringUtils;
import com.kratos.common.utils.StringUtils;
import com.kratos.entity.BaseEntity;
import com.kratos.entity.TreeEntity;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 抽象service提供基本的增删改查操作
 * @author tang he
 * @since 1.0.0
 * @param <T> 增删改查的实体
 */
public abstract class AbstractCrudService<T extends BaseEntity> implements CrudService<T> {
    /**
     * 获取实体Repository
     * @return {@link PageRepository} 实现类
     */
    protected abstract PageRepository<T> getRepository();

    @Override
    public Page<T> findAll(PageRequest pageRequest, Map<String, String[]> param) {
        return getRepository().findAll(getSpecification(param), pageRequest);
    }

    @Override
    public List<T> findAll(Map<String, String[]> param) {
        return getRepository().findAll(getSpecification(param));
    }


    @Override
    public T findOne(String id) {
        return getRepository().findOne(id);
    }

    @Override
    public void logicallyDelete(String id) {
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
    private Specification<T> getSpecification(Map<String, String[]> param) {
        return new SimpleSpecification(param);
    }

    /**
     * 查询条件实现，实现思路：
     * 1、获取当前实体的所有属性并且循环
     * 2、判断属性是否与网页传来的参数的key相同
     * 3、当属性类型为字符串时则用like判断
     * 4、当属性类型为数字时则用equal判断
     * 5、当属性类型为数字时并且value为两个是则用between判断
     */
    class SimpleSpecification implements Specification<T> {
        Map<String, String[]> param;
        String currentKey;
        SimpleSpecification(Map<String, String[]> param) {
            this.param = param;
        }
        @Override
        public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
            Set<Attribute<? super T, ?>> attributes =  root.getModel().getAttributes();
            List<Predicate> predicate = new ArrayList<>();
            String key;
            String[] values;
            for (Attribute<? super T, ?> attribute : attributes) {
                key = attribute.getName();
                Assert.isTrue(
                        !attribute.getJavaType().isPrimitive(),
                        "实体[" + root.getModel().getName() + "]:["+attribute.getName()+"]" +
                                "为基本类型，不要将实体属性设置成基本类型"
                );
                if(!containsKey(param, key))  {
                    continue;
                }

                values = param.get(this.currentKey);

                if(values == null || values[0] == null) {
                    predicate.add(criteriaBuilder.isNull(root.get(key)));
                } else if(attribute.getJavaType().equals(String.class)) {
                    predicate.add(criteriaBuilder.like(root.get(key), "%"+ values[0] +"%"));
                } else if(attribute.getJavaType().getSuperclass().equals(Number.class)) {
                    if(values.length == 1) {
                        predicate.add(criteriaBuilder.equal(root.get(key), values[0]));
                    } else if(values.length == 2) {
                        NumberFormat nf = NumberFormat.getInstance();
                        try {
                            predicate.add(criteriaBuilder.and(
                                    criteriaBuilder.gt(root.get(key), nf.parse(values[0])),
                                    criteriaBuilder.lt(root.get(key), nf.parse(values[1]))
                            ));
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } else if(isBaseEntity(attribute.getJavaType())) {
                    if(StringUtils.isBlank(values[0])) {
                        predicate.add(criteriaBuilder.isNull(root.get(key)));
                    }
                    String field = this.currentKey.split("\\.")[1];
                    EntityManager entityManager = SpringUtils.getBean(EntityManager.class);
                    Query query = entityManager.createQuery("from "+
                            getEntityName(attribute.getJavaType(), root)+" where "+field+"=:" + field);
                    query.setParameter(field, values[0]);
                    List list = query.getResultList();
                    if(!list.isEmpty()) {
                        predicate.add(criteriaBuilder.equal(root.get(key), list));
                    }
                }
            }
            predicate.add(criteriaBuilder.equal(root.get("logicallyDeleted"), false));
            return criteriaBuilder.and(predicate.toArray(new Predicate[]{}));
        }

        private Boolean isBaseEntity(Class<?> clazz) {
            if(clazz == Object.class) {
                return false;
            }
            if(clazz == BaseEntity.class) {
                return true;
            }
            return isBaseEntity(clazz.getSuperclass());
        }

        private String getEntityName(Class<?> clazz, Root<T> root) {
            if(clazz == TreeEntity.class) {
                return root.getJavaType().getSimpleName();
            }
            return clazz.getSimpleName();
        }

        private Boolean containsKey(Map<String, String[]> param, String key) {
            for (String k : param.keySet()) {
                if(k.split("\\.")[0].equals(key)) {
                    this.currentKey = k;
                    return true;
                }
            }
            return false;
        }
    }
}
