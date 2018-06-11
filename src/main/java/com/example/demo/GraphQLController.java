package com.example.demo;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @PostMapping(value = "/graphql", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Map<String, Object> indexFromAnnotated(@RequestBody Map<String, String> request, HttpServletRequest raw) {
//        ExecutionResult executionResult = graphQL.execute(ExecutionInput.newExecutionInput()
//                .query(request.get("query"))
//                .operationName(request.get("operationName"))
//                .context(raw)
//                .build());
//        return executionResult.toSpecification();
        return null;
    }
}
