package com.example.demo;

import com.example.demo.db.DeviceRepository;
import com.example.demo.db.ModuleRepository;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DataFetchers {

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    DataFetcher<List<Module>> modulesFetcher = environment -> moduleRepository.findAll();

    DataFetcher<Module> moduleByIdFetcher = environment -> {
        final String id = environment.getArgument("id");
        return moduleRepository.findById(id).orElse(null);
    };

    DataFetcher<List<Device>> devicesFetcher = environment -> {
        final Module module = environment.getSource();
        final List<String> deviceIdList = module.getDeviceIdList();
        return deviceIdList.stream()
                .map(id -> deviceRepository.findById(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    };
}
