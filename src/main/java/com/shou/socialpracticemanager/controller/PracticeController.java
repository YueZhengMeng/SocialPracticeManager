package com.shou.socialpracticemanager.controller;

import com.shou.socialpracticemanager.po.Group;
import com.shou.socialpracticemanager.po.Practice;
import com.shou.socialpracticemanager.service.PracticeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/practice")
public class PracticeController {

    @Autowired
    private PracticeService practiceService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "获取所有Practice信息",notes = "所有权限")
    public List<Practice> getAllPractice()
    {
        return practiceService.getAllPractice();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "创建一个Practice",notes = "教师使权限")
    int createPractice(@RequestBody String practiceName)
    {
        return practiceService.creatPractice(practiceName);
    }

    @GetMapping("/my")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "获取当前用户加入的Practice",notes = "所有权限")
    List<Practice> getMyPractice()
    {
        return practiceService.getMyPractice();
    }

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "加入一个Practice",notes = "practiceID和GroupID必须\n在practiceID对应的Practice下\n新建一个名为groupName的组\n组内只有当前用户\n所有权限")
    int joinPractice(@RequestBody Group group)
    {
        return practiceService.joinPractice(group);
    }

    @PutMapping(value = "/end/{practiceID}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "结束一个Practice",notes = "教师权限")
    int endPractice(@PathVariable int practiceID)
    {
        return practiceService.endPractice(practiceID);
    }

    @PostMapping(value = "/rename")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "重命名一个Practice",notes = "practiceID和practiceName(新) 必须\n教师权限")
    int renamePractice(@RequestBody Practice practice)
    {
        return practiceService.renamePractice(practice);
    }

    @DeleteMapping("/{practiceID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "删除Practice",notes = "教师权限")
    int deletePractice(@PathVariable int practiceID)
    {
        return practiceService.deletePractice(practiceID);
    }
}
