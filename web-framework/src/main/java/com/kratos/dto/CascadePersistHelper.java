package com.kratos.dto;

import com.kratos.common.CrudService;
import com.kratos.common.utils.ReflectionUtils;
import com.kratos.common.utils.SpringUtils;
import com.kratos.common.utils.StringUtils;
import com.kratos.entity.BaseEntity;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 可以根据Parent和Children注解来保存数据
 * 例：
 * <code>
 * public class GoodsDTO extends BaseDTO<GoodsDTO, Goods> {
 *
 * @Children(service = GoodsAttributeService.class)
 * private List<GoodsAttributeDTO> goodsAttributes;
 * // 省略get set
 * }
 * <p>
 * public class GoodsAttributeDTO extends BaseDTO<GoodsAttributeDTO, GoodsAttribute> {
 * @Parent private String goodsId;
 * // 省略get set
 * }
 * <p>
 * GoodsDTO dto = new GoodsDTO();
 * // 省略赋值
 * CascadePersistHelper.saveChildren(dto);则会相应的将goodsAttributes也进行保存
 * </code>
 */
public class CascadePersistHelper {
    public static <D extends BaseDTO> void saveChildren(D d) {
        List<Field> childrenFields = BaseDTO.getChildrenFields(d.getClass());
        if (!childrenFields.isEmpty()) {
            for (Field childrenField : childrenFields) {
                saveChildren(d, childrenField);
            }
        }
    }

    /**
     * 保存实体d中的子集，需要在DTO中在子集上使用@Children注解并指明service类
     * @param d 父实体
     * @param childrenField 子集的字段
     */
    @SuppressWarnings("unchecked")
    private static <D extends BaseDTO> void saveChildren(final D d, Field childrenField) {
        Children children = childrenField.getAnnotation(Children.class);
        CrudService childCurdService = SpringUtils.getBean(children.service());
        List list = (List) ReflectionUtils.getFieldValue(d, childrenField.getName());
        ParameterizedType type = (ParameterizedType) childrenField.getGenericType();
        Type[] genericTypes = type.getActualTypeArguments();
        saveAsChildren(d, (Class<D>) genericTypes[0], list, childCurdService);
    }

    /**
     * 先删除新传入集合中有的旧集合没有的数据
     * 再更新新集合在旧集合中的数据
     * 然后新增新集合有的旧集合没有的数据
     * @param parent      父对象
     * @param dList       子对象集合
     * @param crudService 子对象service类
     * @param <P>         父对象
     * @param <D>         子对象
     */
    @SuppressWarnings("unchecked")
    private static <P extends BaseDTO,
            D extends BaseDTO> void saveAsChildren(P parent, Class<D> childClass,
                                                      List<D> dList, CrudService crudService) {
        Field parentField = BaseDTO.getParentField(childClass);
        String parentFieldName = parentField.getName();
        String searchField = parentFieldName;
        Parent parentAnnotation = parentField.getAnnotation(Parent.class);
        if(StringUtils.isNotBlank(parentAnnotation.fieldName())) {
            searchField = parentAnnotation.fieldName();
        }
        if (dList != null) {
            for (D d : dList) {
                ReflectionUtils.setFieldValue(d, parentFieldName, parent.getId());
            }
        }

        Map<String, String[]> params = new HashMap<>();
        params.put(searchField, new String[]{ parent.getId() });
        List<BaseEntity> oldEntityList = crudService.findAll(params);
        List<D> oldList = new ArrayList<>();
        if ((oldEntityList == null || oldEntityList.isEmpty()) && dList != null) {
            for (D d : dList) {
                crudService.save(d.convert());
            }
            return;
        }

        if(oldEntityList != null) {
            D d;
            for (BaseEntity e : oldEntityList) {
                try {
                    d = childClass.newInstance();
                    oldList.add((D) d.convertFor(e));
                } catch (InstantiationException | IllegalAccessException e1) {
                    e1.printStackTrace();
                }

            }
        }

        List<D> list = filterNoneIncludes(oldList, dList);
        if(list != null) {
            for (D d : list) {
                if(d != null)
                    crudService.delete(d.getId());
            }
        }
        list = filterIncludes(dList, oldList);
        if(list != null) {
            for (D d : list) {
                if (d != null)
                    crudService.save(d.convert());
            }
        }
        list = filterNoneIncludes(dList, oldList);
        if(list != null) {
            for (D d : list) {
                if (d != null)
                    crudService.save(d.convert());
            }
        }
    }

    private static <D extends BaseDTO> List<D> filterNoneIncludes(List<D> source, List<D> sample) {
        if (source == null) {
            return null;
        }
        if (sample == null) {
            return source;
        }
        return source.stream().filter(old -> sample.stream().noneMatch(d -> d.equals(old)))
                .collect(Collectors.toList());
    }

    private static <D extends BaseDTO> List<D> filterIncludes(List<D> source, List<D> sample) {
        if (source == null) {
            return null;
        }
        if (sample == null) {
            return null;
        }
        return source.stream().filter(data -> sample.stream().anyMatch(d -> d.equals(data)))
                .map(data -> {
                    Field[] fields = ReflectionUtils.getDeclaredFields(BaseDTO.class);
                    D old = sample.stream().filter(d -> d.equals(data)).findFirst().get();
                    for (Field field : fields) {
                        ReflectionUtils.setFieldValue(data, field.getName(),
                                ReflectionUtils.getFieldValue(old, field.getName()));
                    }
                    return data;
                })
                .collect(Collectors.toList());
    }
}
