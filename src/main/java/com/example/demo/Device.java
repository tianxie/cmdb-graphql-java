package com.example.demo;

import lombok.Getter;

@Getter
public class Device {
    final String id;
    final String name;

    public Device(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
