package com.kratos.module.auth.service;

import com.kratos.common.AbstractCrudService;
import com.kratos.common.PageRepository;
import com.kratos.module.auth.domain.Module;
import com.kratos.module.auth.domain.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class ModuleServiceImpl extends AbstractCrudService<Module> implements ModuleService {
    private final ModuleRepository moduleRepository;



    @Override
    protected PageRepository<Module> getRepository() {
        return moduleRepository;
    }

    @Autowired
    public ModuleServiceImpl(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }
}
