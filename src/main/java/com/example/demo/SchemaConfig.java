package com.example.demo;

import graphql.schema.DataFetcher;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLList.list;
import static graphql.schema.GraphQLObjectType.newObject;

@Configuration
public class SchemaConfig {

    @Bean
    GraphQLSchema schema() {
        DataFetcher modulesFetcher = environment -> MockData.moduleData.values();

        final GraphQLObjectType deviceType = newObject()
                .name("Device")
                .field(field -> field
                        .name("id")
                        .type(GraphQLString)
                )
                .field(field -> field
                        .name("name")
                        .type(GraphQLString)
                )
                .build();


        DataFetcher devicesFetcher = environment -> {
            final Module module = environment.getSource();
            return module.getDeviceList();
        };

        final GraphQLObjectType moduleType = newObject()
                .name("Module")
                .field(field -> field
                        .name("id")
                        .type(GraphQLString)
                )
                .field(field -> field
                        .name("name")
                        .type(GraphQLString)
                )
                .field(field -> field
                        .name("devices")
                        .type(list(deviceType))
                        .dataFetcher(devicesFetcher)
                )
                .build();

        DataFetcher<Module> moduleByIdFetcher = environment -> {
            final Object id = environment.getArgument("id");
            return MockData.moduleData.get(id);
        };

        return GraphQLSchema.newSchema()
                .query(newObject()
                        .name("query")
                        .field(field -> field
                                .name("modules")
                                .type(list(moduleType))
                                .dataFetcher(modulesFetcher)
                        )
                        .field(field -> field
                                .name("module")
                                .argument(arg -> arg
                                        .name("id")
                                        .type(GraphQLString)
                                )
                                .type(moduleType)
                                .dataFetcher(moduleByIdFetcher)
                        )
                        .build())
                .build();
    }

}
