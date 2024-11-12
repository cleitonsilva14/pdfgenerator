package com.app.pdfgenerator.generator;


import com.app.pdfgenerator.mapper.DataMapper;
import com.app.pdfgenerator.model.Employee;
import com.app.pdfgenerator.document.DocumentGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.List;

@RestController
public class Document {

    @Autowired
    private SpringTemplateEngine sprStringTemplateEngine;

    @Autowired
    private DataMapper dataMapper;

    @Autowired
    private DocumentGenerator documentGenerator;

    @PostMapping(value = "/generate/document")
    public String generateDocument(@RequestBody List<Employee> employeeList){
        String html = "";

        Context dataContext = dataMapper.setData(employeeList);

        html = sprStringTemplateEngine.process("template", dataContext);

        documentGenerator.htmlToPDF(html);

        return "Success";

    }

}
