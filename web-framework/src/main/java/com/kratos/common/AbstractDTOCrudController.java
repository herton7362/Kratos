package com.kratos.common;

import com.kratos.dto.BaseDTO;
import com.kratos.entity.BaseEntity;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 提供基本增删改查使用DTO
 * @author tang he
 * @since 1.0.1
 * @param <T> 实现增删改查的实体
 */
public abstract class AbstractDTOCrudController<D extends BaseDTO<D, T>, T extends BaseEntity> {
    /**
     * 获取实体的service
     * @return {@link CrudService} 实现类
     */
    @Autowired
    protected CrudService<T> crudService;
    /**
     * 获取实体的service
     * @return {@link CrudService} 实现类
     */
    protected CrudService<T> getService() {
        return this.crudService;
    }

    /**
     * 查询
     */
    @ApiOperation(value="分页如果不传页数则按照条件查询", notes = "可以传查询条件例：name=张三，查询条件使用or分割")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "currentPage", value = "当前页数", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页页数", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "sort", value = "排序属性，多个用逗号隔开", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "order", value = "排序方向，多个用逗号隔开", dataType = "String", paramType = "query")
    })
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> searchPagedList(@ModelAttribute PageParam pageParam, HttpServletRequest request) {
        Map<String, String[]> param = request.getParameterMap();
        if(pageParam.isPageAble()) {
            PageResult<T> page = getService().findAll(pageParam.getPageRequest(), param);
            return new ResponseEntity<>(page, HttpStatus.OK);
        }
        List<T> list = getService().findAll(param);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * 查询一个
     */
    @ApiOperation(value="查询一个")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<? extends T> getOne(@PathVariable String id) {
        return new ResponseEntity<>(getService().findOne(id), HttpStatus.OK);
    }

    /**
     * 保存
     */
    @ApiOperation(value="保存")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<T> save(@RequestBody T t) {
        t = getService().save(t);
        return new ResponseEntity<>(t, HttpStatus.OK);
    }

    /**
     * 删除
     */
    @ApiOperation(value="删除默认为逻辑删除")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<T> delete(@PathVariable String id) {
        String[] ids = id.split(",");
        for (String s : ids) {
            getService().delete(s);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 调整排序
     */
    @ApiOperation(value="调整排序")
    @RequestMapping(value = "/sort", method = RequestMethod.POST)
    public ResponseEntity<T> sort(@RequestBody List<T> ts) {
        getService().sort(ts);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
