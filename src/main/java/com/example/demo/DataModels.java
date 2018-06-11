package com.example.demo;

import graphql.schema.GraphQLObjectType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLList.list;
import static graphql.schema.GraphQLObjectType.newObject;

@Component
public class DataModels {

    @Autowired
    private DataFetchers dataFetchers;

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
//            .field(field -> field
//                    .name("devices")
//                    .type(list(deviceType))
//                    .dataFetcher(dataFetchers.devicesFetcher)
//            )
            .build();
}
