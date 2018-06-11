package com.example.demo;

import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Document(collection = "project_module")
public class Module {
    @Id
    private String id;

    private String mod_name;

    @Field("device")
    private List<DeviceId> deviceIdList;

    public List<String> getDeviceIdStringList() {
        return deviceIdList.stream()
                .map(deviceId -> deviceId.id)
                .collect(Collectors.toList());
    }

    class DeviceId {
        @Field("id")
        String id;
    }
}
