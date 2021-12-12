package com.shou.socialpracticemanager.controller;


import com.shou.socialpracticemanager.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

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
