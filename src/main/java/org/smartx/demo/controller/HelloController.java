package org.smartx.demo.controller;

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

    @RequestMapping("/")
    public String index() {
        return "Hello Spring Boot!" + appName;
    }

}
