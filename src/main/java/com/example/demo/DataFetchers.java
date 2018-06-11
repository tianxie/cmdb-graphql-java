package com.example.demo;

import com.example.demo.db.ModuleRepository;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataFetchers {

    @Autowired
    private ModuleRepository moduleRepository;

    DataFetcher modulesFetcher = environment -> moduleRepository.findAll();

    DataFetcher<Module> moduleByIdFetcher = environment -> {
        final String id = environment.getArgument("id");
        return moduleRepository.findById(id).get(); // TODO
    };

    DataFetcher devicesFetcher = environment -> {
        final Module module = environment.getSource();
        return module.getDeviceIdList();
    };
}
