package com.kratos.service;

import com.kratos.repository.FormRepository;
import com.kratos.entity.Form;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("formService")
@Transactional
public class FormServiceImpl implements FormService {
    private final FormRepository formRepository;
    public FormServiceImpl(FormRepository formRepository) {
        this.formRepository = formRepository;
    }
    public void save(Form baseForm) {
        formRepository.save(baseForm);
    }
}
