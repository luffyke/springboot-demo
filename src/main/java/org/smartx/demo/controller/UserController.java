package org.smartx.demo.controller;

import org.smartx.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </p>
 *
 * <b>Creation Time:</b> 16/12/4
 *
 * @author kext
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/user/get")
    public String get(@RequestParam(value = "id") Integer id) {
        String name = userService.findNameById(id);
        if (null != name) {
            return "user name is " + name;
        } else {
            return "User not found";
        }
    }
}
