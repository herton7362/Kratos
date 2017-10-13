package com.kratos.module.dictionary.web;

import com.kratos.common.AbstractCrudController;
import com.kratos.common.CrudService;
import com.kratos.module.dictionary.domain.Dictionary;
import com.kratos.module.dictionary.service.DictionaryCategoryService;
import com.kratos.module.dictionary.service.DictionaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "字典管理")
@RestController
@RequestMapping("/api/dictionary")
public class DictionaryController extends AbstractCrudController<Dictionary> {
    private final DictionaryService dictionaryService;
    private final DictionaryCategoryService dictionaryCategoryService;
    @Override
    protected CrudService<Dictionary> getService() {
        return dictionaryService;
    }

    /**
     * 保存
     */
    @ApiOperation(value="保存")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Dictionary> save(@RequestBody Dictionary product) throws Exception {
        if(product.getDictionaryCategory() != null && StringUtils.isNotBlank(product.getDictionaryCategory().getId())) {
            product.setDictionaryCategory(dictionaryCategoryService.findOne(product.getDictionaryCategory().getId()));
        } else {
            product.setDictionaryCategory(null);
        }
        product = dictionaryService.save(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @Autowired
    public DictionaryController(
            DictionaryService dictionaryService,
            DictionaryCategoryService dictionaryCategoryService
    ) {
        this.dictionaryService = dictionaryService;
        this.dictionaryCategoryService = dictionaryCategoryService;
    }
}
