package com.charan.chidori.controller;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RefreshScope
@RestController
@RequestMapping("/properties")
public class PropertiesDisplayController {

    @Value("${my.application-name:default-app}")
    private String appName;

    @Value("${db.name:default-db}")
    private String dbName;

    @PostConstruct
    public void init() {
        log.info("Bean initialized with appName {}, dbName {}", appName, dbName);
    }

    @GetMapping("/appName")
    public String displayApplicationName() {
        return appName;
    }

    @GetMapping("/dbName")
    public String displayDbName() {
        return dbName;
    }

}
