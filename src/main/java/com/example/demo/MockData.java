package com.example.demo;

import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.Arrays.asList;

public class MockData {

    static Device device1 = new Device(
            "1003",
            "Leia Organa"
    );

    static Device device2 = new Device(
            "1004",
            "Wilhuff Tarkin"
    );

    static Module module1 = new Module(
            "1000",
            "Luke Skywalker",
            asList(device1, device2)
    );

    static Module module2 = new Module(
            "1001",
            "Darth Vader",
            asList(device2, device1)
    );

    static Map<String, Module> moduleData = new LinkedHashMap<>();

    static {
        moduleData.put("1000", module1);
        moduleData.put("1001", module2);
    }

    public static Object getModule(String id) {
        return moduleData.get(id);
    }
}
