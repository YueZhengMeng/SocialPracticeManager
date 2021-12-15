package com.shou.socialpracticemanager.controller;


import com.shou.socialpracticemanager.po.Group;
import com.shou.socialpracticemanager.service.GroupService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "获取所有Group信息",notes = "管理员用")
    public List<Group> getAllGroup()
    {
        return groupService.getAllGroup();
    }

    @GetMapping("/ByPracticeID/{practiceID}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "获取某个Practice拥有的Group的信息",notes = "通用")
    public List<Group> getGroupByPracticeID(@PathVariable int practiceID)
    {
        return groupService.getGroupByPracticeID(practiceID);
    }

    @GetMapping("/teacherGroup/{practiceID}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "获取某个Practice的教师Group的信息",notes = "通用")
    public Group getTeacherGroup(@PathVariable int practiceID)
    {
        return groupService.getTeacherGroup(practiceID);
    }

    @GetMapping("/ByActivityID/{activityID}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "获取参与了某个Activity的Group信息",notes = "通用")
    public List<Group> getGroupByActivityID(@PathVariable int activityID)
    {
        return groupService.getGroupByActivityID(activityID);
    }

    @GetMapping("/my")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "获取当前用户的Group信息",notes = "通用")
    public List<Group> getMyGroup()
    {
        return groupService.getMyGroup();
    }

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "当前用户加入某个Group",notes = "通用")
    int joinGroup(@RequestBody int groupID) {
        return groupService.joinGroup(groupID);
    }

    @DeleteMapping("/{groupID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "当前用户离开某个Group",notes = "通用")
    int leaveGroup(@PathVariable int groupID)
    {
        return groupService.leaveGroup(groupID);
    }
}
