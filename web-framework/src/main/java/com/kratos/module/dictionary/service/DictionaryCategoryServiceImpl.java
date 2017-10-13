package com.kratos.module.dictionary.service;

import com.kratos.common.AbstractCrudService;
import com.kratos.common.PageRepository;
import com.kratos.module.dictionary.domain.DictionaryCategory;
import com.kratos.module.dictionary.domain.DictionaryCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class DictionaryCategoryServiceImpl extends AbstractCrudService<DictionaryCategory> implements DictionaryCategoryService {
    private final DictionaryCategoryRepository dictionaryCategoryRepository;
    @Override
    protected PageRepository<DictionaryCategory> getRepository() {
        return dictionaryCategoryRepository;
    }

    @Autowired
    public DictionaryCategoryServiceImpl(DictionaryCategoryRepository dictionaryCategoryRepository) {
        this.dictionaryCategoryRepository = dictionaryCategoryRepository;
    }
}
