package com.kratos.common;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.kratos.entity.TreeEntity;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 提供基本增删改查和树形的查询
 * @author tang he
 * @since 1.0.0
 * @param <T> 实现增删改查的实体
 */
public abstract class AbstractTreeController<T extends TreeEntity> extends AbstractCrudController<T> {
    /**
     * 查询
     */
    @Override
    @ApiOperation(value="分页如果不传页数则按照条件查询", notes = "可以传查询条件例：name=张三，查询条件使用or分割")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "currentPage", value = "当前页数", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页页数", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "sort", value = "排序属性，多个用逗号隔开", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "order", value = "排序方向，多个用逗号隔开", dataType = "String", paramType = "query")
    })
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> searchPagedList(@ModelAttribute PageParam pageParam, HttpServletRequest request) throws Exception {
        Map<String, String[]> param = request.getParameterMap();
        if(pageParam.isPageAble()) {
            Page<T> page = getService().findAll(pageParam.getPageRequest(), param);

            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(page);
            mappingJacksonValue.setFilters(new SimpleFilterProvider().addFilter(
                    TreeEntity.CUSTOMER_FILTER, SimpleBeanPropertyFilter.serializeAllExcept("parent")));
            return new ResponseEntity<>(mappingJacksonValue, HttpStatus.OK);
        }
        List<T> list = getService().findAll(param);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);
        mappingJacksonValue.setFilters(new SimpleFilterProvider().addFilter(
                TreeEntity.CUSTOMER_FILTER, SimpleBeanPropertyFilter.serializeAllExcept("parent")));
        return new ResponseEntity<>(mappingJacksonValue, HttpStatus.OK);
    }

    /**
     * 查询一个
     */
    @Override
    @ApiOperation(value="查询一个")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getOne(@PathVariable String id) throws Exception {
        T t = getService().findOne(id);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(t);
        mappingJacksonValue.setFilters(new SimpleFilterProvider().addFilter(
                TreeEntity.CUSTOMER_FILTER, SimpleBeanPropertyFilter.serializeAllExcept("children")));
        return new ResponseEntity<>(mappingJacksonValue, HttpStatus.OK);
    }
}
