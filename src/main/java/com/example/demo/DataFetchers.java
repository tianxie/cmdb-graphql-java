package com.example.demo;

import com.example.demo.db.DeviceRepository;
import com.example.demo.db.ModuleRepository;
import graphql.schema.DataFetcher;
import org.dataloader.DataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataFetchers {

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    @Qualifier("moduleDataLoader")
    private DataLoader<String, Module> moduleDataLoader;

    @Autowired
    @Qualifier("deviceDataLoader")
    private DataLoader<String, Device> deviceDataLoader;

    DataFetcher<List<Module>> allModulesFetcher = environment -> moduleRepository.findAll();

    DataFetcher<List<Device>> allDevicesFetcher = environment -> deviceRepository.findAll();

    DataFetcher moduleByIdFetcher = environment -> {
        final String id = environment.getArgument("id");
        return moduleDataLoader.load(id);
    };

    DataFetcher devicesOfModuleFetcher = environment -> {
        final Module module = environment.getSource();
        final List<String> deviceIdList = module.getDeviceIdList();
        return deviceDataLoader.loadMany(deviceIdList);
    };
}
