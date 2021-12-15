package com.shou.socialpracticemanager.controller;

import com.shou.socialpracticemanager.po.User;
import com.shou.socialpracticemanager.service.UserService;
import io.swagger.annotations.ApiOperation;
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

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "用户注册",notes = "通用")
    int registerUser(@RequestBody User user)
    {
        return userService.registerUser(user);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "查看所有User信息",notes = "管理员用")
    List<User> getAllUser()
    {
        return userService.getAllUser();
    }

    @GetMapping("/byGroupID/{groupID}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "查询某Group拥有的用户的信息",notes = "通用")
    List<User> getUserByGroupID(@PathVariable int groupID)
    {
        return userService.getUserByGroupID(groupID);
    }
}
