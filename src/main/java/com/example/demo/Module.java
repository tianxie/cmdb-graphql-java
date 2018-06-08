package com.example.demo;

import java.util.List;

public class Module {
    final String id;
    final String name;
    final List<Device> deviceList;

    public Module(String id, String name, List<Device> deviceList) {
        this.id = id;
        this.name = name;
        this.deviceList = deviceList;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Device> getDeviceList() {
        return deviceList;
    }
}
