package com.charan.chidori.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/properties")
public class PropertiesDisplayController {

    @Value("${my.application-name:default-app}")
    private String appName;

    @Value("${db.name:default-db}")
    private String dbName;

    @GetMapping("/appName")
    public String displayApplicationName() {
        return appName;
    }

    @GetMapping("/dbName")
    public String displayDbName() {
        return dbName;
    }

}
