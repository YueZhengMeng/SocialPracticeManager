package com.shou.socialpracticemanager.controller;


import com.shou.socialpracticemanager.dto.GroupMessage;
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
    @ApiOperation(value = "获取所有Group信息",notes = "所有权限")
    public List<GroupMessage> getAllGroup()
    {
        return groupService.getAllGroup();
    }

    @GetMapping("/ByPracticeID/{practiceID}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "获取某个Practice拥有的Group的信息",notes = "所有权限")
    public List<GroupMessage> getGroupByPracticeID(@PathVariable int practiceID)
    {
        return groupService.getGroupByPracticeID(practiceID);
    }

    @GetMapping("/teacherGroup/{practiceID}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "获取某个Practice的教师Group的信息",notes = "所有权限")
    public GroupMessage getTeacherGroup(@PathVariable int practiceID)
    {
        return groupService.getTeacherGroup(practiceID);
    }

    @GetMapping("/ByActivityID/{activityID}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "获取参与了某个Activity的Group信息",notes = "所有权限")
    public List<Group> getGroupByActivityID(@PathVariable int activityID)
    {
        return groupService.getGroupByActivityID(activityID);
    }

    @GetMapping("/my")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "获取当前用户的Group信息",notes = "所有权限")
    public List<GroupMessage> getMyGroup()
    {
        return groupService.getMyGroup();
    }

    @PutMapping(value = "/rename")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "重命名一个Group",notes = "只需要GroupID和GroupName(新)\n所有权限")
    int renameGroup(@RequestBody Group group)
    {
        return groupService.renameGroup(group);
    }

    @PutMapping(value = "/score")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "为一个Group打分",notes = "只需要GroupID和score\n教师权限")
    int setGroupScore(@RequestBody Group group)
    {
        return groupService.setGroupScore(group);
    }

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "当前用户加入某个Group",notes = "只需要groupID\n所有权限")
    int joinGroup(@RequestBody Group group) {
        return groupService.joinGroup(group.getGroupID());
    }

    @DeleteMapping("/{groupID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "当前用户离开某个Group",notes = "即退出某个Practice\n所有权限")
    int leaveGroup(@PathVariable int groupID)
    {
        return groupService.leaveGroup(groupID);
    }
}
