package com.kratos.common;

import com.kratos.dto.BaseDTO;
import com.kratos.entity.BaseEntity;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Map;

public interface DTOCrudService<D extends BaseDTO<D, T>, T extends BaseEntity> {
    /**
     * 分页查询
     * @param pageRequest 分页条件
     * @param param 查询条件
     * @return {@link PageResult} spring boot 分页类
     */
    PageResult<D> findAll(PageRequest pageRequest, Map<String, String[]> param);

    /**
     * 列表查询
     * @param param 查询条件
     * @return 实体列表
     */
    List<D> findAll(Map<String, String[]> param);

    /**
     * 查询一个
     * @param id 主键
     * @return 实体
     */
    D findOne(String id);

    /**
     * 删除
     * @param id 主键
     */
    void delete(String id);

    /**
     * 新增或修改
     * @param t 实体，如果主键不为空则修改，为空则保存
     * @return 保存后的实体
     */
    D save(D t);

    /**
     * 调整排序
     * @param ts 调整后的顺序列表
     */
    void sort(List<T> ts);
}
