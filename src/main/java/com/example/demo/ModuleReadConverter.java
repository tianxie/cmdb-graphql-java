//package com.example.demo;
//
//import org.bson.Document;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.data.convert.ReadingConverter;
//
//import java.util.Collections;
//
//@ReadingConverter
//public class ModuleReadConverter implements Converter<Document, Module> {
//
//    public Module convert(Document source) {
//        final String id = source.getObjectId("_id").toHexString();
//        final String moduleName = source.getString("mod_name");
//        final Module.DeviceId device = source.get("device", Module.DeviceId.class);
//        final Module module = new Module(id, moduleName, Collections.emptyList());
//    }
//}
