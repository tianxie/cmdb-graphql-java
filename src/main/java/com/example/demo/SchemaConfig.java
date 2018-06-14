package com.example.demo;

import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLList.list;
import static graphql.schema.GraphQLObjectType.newObject;

@Configuration
public class SchemaConfig {

    @Autowired
    private DataFetchers dataFetchers;

    @Bean
    GraphQLSchema schema() {
        final GraphQLObjectType deviceType = newObject()
                .name("Device")
                .field(field -> field
                        .name("id")
                        .type(GraphQLString)
                )
                .field(field -> field
                        .name("hostname")
                        .type(GraphQLString)
                )
                .build();

        final GraphQLObjectType moduleType = newObject()
                .name("Module")
                .field(field -> field
                        .name("id")
                        .type(GraphQLString)
                )
                .field(field -> field
                        .name("mod_name")
                        .type(GraphQLString)
                )
                .field(field -> field
                        .name("devices")
                        .type(list(deviceType))
                        .dataFetcher(dataFetchers.devicesOfModuleFetcher)
                )
                .build();

        return GraphQLSchema.newSchema()
                .query(newObject()
                        .name("query")
                        .field(field -> field
                                .name("modules")
                                .type(list(moduleType))
                                .dataFetcher(dataFetchers.allModulesFetcher)
                        )
                        .field(field -> field
                                .name("module")
                                .argument(arg -> arg
                                        .name("id")
                                        .type(GraphQLString)
                                )
                                .type(moduleType)
                                .dataFetcher(dataFetchers.moduleByIdFetcher)
                        )
                        .build())
                .build();
    }

}
