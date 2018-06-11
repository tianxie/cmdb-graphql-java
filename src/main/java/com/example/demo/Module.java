package com.example.demo;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Document(collection = "project_module")
public class Module {
    @Id
    private String id;

    private String mod_name;

    @Field("device")
    private List<String> deviceIdList;
}
