package com.kratos.module.auth.service;

import com.kratos.common.AbstractCrudService;
import com.kratos.common.PageRepository;
import com.kratos.exceptions.BusinessException;
import com.kratos.module.auth.domain.Module;
import com.kratos.module.auth.domain.Role;
import com.kratos.module.auth.domain.RoleRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class RoleServiceImpl extends AbstractCrudService<Role> implements RoleService {
    private final RoleRepository roleRepository;
    private final ModuleService moduleService;
    @Override
    protected PageRepository<Role> getRepository() {
        return roleRepository;
    }

    @Override
    public void authorize(String roleId, List<String> moduleIds) throws Exception {
        if(StringUtils.isBlank(roleId)) {
            throw new BusinessException("roleId is required");
        }
        Role role = roleRepository.findOne(roleId);
        List<Module> modules = new ArrayList<>(moduleIds.size());
        moduleIds.forEach(moduleId -> modules.add(moduleService.findOne(moduleId)));
        role.setModules(modules);
        roleRepository.save(role);
    }

    @Autowired
    public RoleServiceImpl(
            RoleRepository roleRepository,
            ModuleService moduleService
    ) {
        this.roleRepository = roleRepository;
        this.moduleService = moduleService;
    }
}
