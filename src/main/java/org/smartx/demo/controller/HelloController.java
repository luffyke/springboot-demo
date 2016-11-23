package org.smartx.demo.controller;

import org.smartx.demo.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </p>
 *
 * <b>Creation Time:</b> 16/11/15
 *
 * @author kext
 */
@RestController
public class HelloController {

    @Value("${app.name}")
    private String appName;

    @Autowired
    private AppConfig appConfig;

    @RequestMapping("/")
    public String index() {
        return "Hello Spring Boot!";
    }

    @RequestMapping("/app")
    public String app() {
        return appConfig.getName() + "\n" + appConfig.getDescription();
    }

}
