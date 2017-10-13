package com.kratos.module.dictionary.service;

import com.kratos.common.AbstractCrudService;
import com.kratos.common.PageRepository;
import com.kratos.module.dictionary.domain.Dictionary;
import com.kratos.module.dictionary.domain.DictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class DictionaryServiceImpl extends AbstractCrudService<Dictionary> implements DictionaryService {
    private final DictionaryRepository dictionaryRepository;
    @Override
    protected PageRepository<Dictionary> getRepository() {
        return dictionaryRepository;
    }

    @Autowired
    public DictionaryServiceImpl(DictionaryRepository dictionaryRepository) {
        this.dictionaryRepository = dictionaryRepository;
    }
}
