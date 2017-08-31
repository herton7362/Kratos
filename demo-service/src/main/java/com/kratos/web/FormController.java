package com.kratos.web;

import com.kratos.entity.Form;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kratos.service.FormService;

import java.util.UUID;

@RestController
public class FormController {

    private final FormService formService;

    @Autowired
    public FormController(FormService formService) {
        this.formService = formService;
    }

    @RequestMapping("/")
    public String index() {
        Form baseForm = new Form();
        baseForm.setId(UUID.randomUUID().toString());
        formService.save(baseForm);
        return "Hello world!";
    }
}
