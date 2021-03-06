package com.example.demo;

import com.example.demo.db.DeviceRepository;
import com.example.demo.db.ModuleRepository;
import graphql.execution.instrumentation.ChainedInstrumentation;
import graphql.execution.instrumentation.Instrumentation;
import graphql.execution.instrumentation.dataloader.DataLoaderDispatcherInstrumentation;
import graphql.execution.instrumentation.tracing.TracingInstrumentation;
import org.dataloader.BatchLoader;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static graphql.execution.instrumentation.dataloader.DataLoaderDispatcherInstrumentationOptions.newOptions;
import static java.util.Arrays.asList;

@Component
@Configuration
public class DataLoaderConfig {

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    @Bean
    public Instrumentation instrumentation() {
        DataLoaderRegistry registry = new DataLoaderRegistry();
        registry.register("moduleDataLoader", moduleDataLoader());
        registry.register("deviceDataLoader", deviceDataLoader());
        final DataLoaderDispatcherInstrumentation dataLoaderDispatcherInstrumentation = new DataLoaderDispatcherInstrumentation(registry, newOptions().includeStatistics(true));
        return new ChainedInstrumentation(
                asList(new TracingInstrumentation(), dataLoaderDispatcherInstrumentation)
        );
    }

    @Bean("deviceDataLoader")
    public DataLoader<String, Device> deviceDataLoader() {
        BatchLoader<String, Device> batchLoader = keys -> {
            Supplier<List<Device>> supplier = () ->
                    keys.stream()
                            .map(deviceRepository::findById)
                            .map(opt -> opt.orElse(null))
                            .collect(Collectors.toList());

            return CompletableFuture.supplyAsync(supplier);
        };
        return new DataLoader<>(batchLoader);
    }

    @Bean("moduleDataLoader")
    public DataLoader<String, Module> moduleDataLoader() {
        BatchLoader<String, Module> batchLoader = keys -> {
            Supplier<List<Module>> supplier = () ->
                    keys.stream()
                            .map(moduleRepository::findById)
                            .map(opt -> opt.orElse(null))
                            .collect(Collectors.toList());

            return CompletableFuture.supplyAsync(supplier);
        };
        return new DataLoader<>(batchLoader);
    }
}
