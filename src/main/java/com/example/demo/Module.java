package com.example.demo;

import lombok.Getter;

import java.util.List;

@Getter
public class Module {
    final String id;
    final String mod_name;
    final List<Device> deviceList;

    public Module(String id, String name, List<Device> deviceList) {
        this.id = id;
        this.mod_name = name;
        this.deviceList = deviceList;
    }
}
