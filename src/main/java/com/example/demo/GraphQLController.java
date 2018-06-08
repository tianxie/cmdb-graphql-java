package com.example.demo;

import graphql.ExecutionInput;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static graphql.ExecutionInput.newExecutionInput;

//@RestController
public class GraphQLController {

    @GetMapping(value = "/graphql")
    public void handleGet(@RequestParam String query,
                          @RequestParam String operationName,
                          @RequestParam Map<String, Object> variables) {
        if (query == null) {

        }

        ExecutionInput.Builder executionInput = newExecutionInput()
                .query(query)
                .operationName(operationName)
                .variables(variables);

        DataWiring.Context context = new DataWiring.Context();
        executionInput.context(context);
    }
}
