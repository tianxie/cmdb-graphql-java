package com.example.demo;

import com.google.gson.Gson;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Document(collection = "project_module")
public class Module {
    @Id
    private String id;

    private String mod_name;

    private List<String> device;

    public List<String> getDeviceIdList() {
        Gson gson = new Gson();
        return device.stream()
                .map(s -> gson.fromJson(s, DeviceId.class))
                .map(deviceId -> deviceId.id)
                .collect(Collectors.toList());
    }

    private class DeviceId {
        String id;
    }
}
