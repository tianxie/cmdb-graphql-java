package com.example.demo;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "device")
public class Device {
    @Id
    private String id;

    private String name;
}
