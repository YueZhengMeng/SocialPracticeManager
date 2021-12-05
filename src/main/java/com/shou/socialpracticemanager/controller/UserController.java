package com.shou.socialpracticemanager.controller;

import com.shou.socialpracticemanager.po.User;
import com.shou.socialpracticemanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    List<User> getAllUser()
    {
        return userService.getAllUser();
    }

    @GetMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    String test()
    {
        return "ok";
    }
}
