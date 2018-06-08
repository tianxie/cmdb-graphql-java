package com.example.demo;

import org.dataloader.BatchLoader;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderRegistry;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class DataWiring {
    /**
     * The context object is passed to each level of a graphql query and in this case it contains
     * the data loader registry.  This allows us to keep our data loaders per request since
     * they cache data and cross request caches are often not what you want.
     */
    public static class Context {

        final DataLoaderRegistry dataLoaderRegistry;

        public Context() {
            this.dataLoaderRegistry = new DataLoaderRegistry();
            dataLoaderRegistry.register("modules", newModuleDataLoader());
        }

        public DataLoaderRegistry getDataLoaderRegistry() {
            return dataLoaderRegistry;
        }

        public DataLoader<String, Object> getCharacterDataLoader() {
            return dataLoaderRegistry.getDataLoader("modules");
        }
    }

    private static List<Object> getModulesFromMongo(List<String> keys) {
        return keys.stream().map(MockData::getModule).collect(Collectors.toList());
    }

    // a batch loader function that will be called with N or more keys for batch loading
    private static BatchLoader<String, Object> moduleBatchLoader = keys -> {

        //
        // we are using multi threading here.  Imagine if getCharacterDataViaBatchHTTPApi was
        // actually a HTTP call - its not here - but it could be done asynchronously as
        // a batch API call say
        //
        //
        // direct return of values
        //CompletableFuture.completedFuture(getCharacterDataViaBatchHTTPApi(keys))
        //
        // or
        //
        // async supply of values
        return CompletableFuture.supplyAsync(() -> getModulesFromMongo(keys));
    };

    private static DataLoader<String, Object> newModuleDataLoader() {
        return new DataLoader<>(moduleBatchLoader);
    }
}
