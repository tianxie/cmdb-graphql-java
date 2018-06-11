package com.example.demo;

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

    @Autowired
    private DataModels dataModels;

    @Bean
    GraphQLSchema schema() {
        return GraphQLSchema.newSchema()
                .query(newObject()
                        .name("query")
                        .field(field -> field
                                .name("modules")
                                .type(list(dataModels.moduleType))
                                .dataFetcher(dataFetchers.modulesFetcher)
                        )
                        .field(field -> field
                                .name("module")
                                .argument(arg -> arg
                                        .name("id")
                                        .type(GraphQLString)
                                )
                                .type(dataModels.moduleType)
                                .dataFetcher(dataFetchers.moduleByIdFetcher)
                        )
                        .build())
                .build();
    }

}
