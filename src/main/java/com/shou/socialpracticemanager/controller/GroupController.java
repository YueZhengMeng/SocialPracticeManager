package com.shou.socialpracticemanager.controller;


import com.shou.socialpracticemanager.po.Group;
import com.shou.socialpracticemanager.service.GroupService;
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
    public List<Group> getAllGroup()
    {
        return groupService.getAllGroup();
    }

    @GetMapping("/ByPracticeID/{practiceID}")
    @ResponseStatus(HttpStatus.OK)
    public List<Group> getGroupByPracticeID(@PathVariable int practiceID)
    {
        return groupService.getGroupByPracticeID(practiceID);
    }

    @GetMapping("/teacherGroup/{practiceID}")
    @ResponseStatus(HttpStatus.OK)
    public Group getTeacherGroup(@PathVariable int practiceID)
    {
        return groupService.getTeacherGroup(practiceID);
    }

    @GetMapping("/ByActivityID/{activityID}")
    @ResponseStatus(HttpStatus.OK)
    public List<Group> getGroupByActivityID(@PathVariable int activityID)
    {
        return groupService.getGroupByActivityID(activityID);
    }

    @GetMapping("/my")
    @ResponseStatus(HttpStatus.OK)
    public List<Group> getMyGroup()
    {
        return groupService.getMyGroup();
    }

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.CREATED)
    int joinGroup(@RequestParam int groupID) {
        return groupService.joinGroup(groupID);
    }

    @DeleteMapping("/{groupID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    int leaveGroup(@PathVariable int groupID)
    {
        return groupService.leaveGroup(groupID);
    }
}
