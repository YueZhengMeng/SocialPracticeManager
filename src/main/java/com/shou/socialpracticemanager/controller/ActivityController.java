package com.shou.socialpracticemanager.controller;

import com.shou.socialpracticemanager.dto.ActivityMessage;
import com.shou.socialpracticemanager.dto.FinishStateMessage;
import com.shou.socialpracticemanager.po.Activity;
import com.shou.socialpracticemanager.po.ActivityParticipation;
import com.shou.socialpracticemanager.service.ActivityService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/activity")
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "获取所有Activity信息",notes = "所有权限")
    public List<ActivityMessage> getAllActivity() {
        return activityService.getAllActivity();
    }

    @GetMapping("/my")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "获取当前用户的Activity信息",notes = "所有权限")
    public List<ActivityMessage> getMyActivity() {
        return activityService.getMyActivity();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "新建Activity",notes = "只需要activityName和practiceID\n教师权限")
    public int createActivity(@RequestBody Activity activity)
    {
        return activityService.createActivity(activity);
    }

    @GetMapping("/ByPracticeID/{practiceID}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "获取某个Practice拥有的所有Activity信息",notes = "所有权限")
    public List<ActivityMessage> getActivityByPracticeID(@PathVariable int practiceID)
    {
        return activityService.getActivityByPracticeID(practiceID);
    }

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "某个Group加入某个Activity",notes = "只需要activityID和groupID\n所有权限")
    public int joinActivity(@RequestBody ActivityParticipation activityParticipation)
    {
        return activityService.joinActivity(activityParticipation);
    }

    @GetMapping("/finishState/{activityID}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "某个Activity的所有Group完成情况",notes = "只需要activityID\n所有权限")
    public List<FinishStateMessage> getActivityState(@PathVariable int activityID)
    {
        return activityService.getActivityState(activityID);
    }

    @PutMapping("/finish")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "某个Group完成一个Activity",notes = "只需要activityID和GroupID\n所有权限")
    public int finishActivity(@RequestBody ActivityParticipation activityParticipation)
    {
        return activityService.finishActivity(activityParticipation);
    }

    @PutMapping(value = "/end/{activityID}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "结束一个Activity",notes = "教师权限")
    int endActivity(@PathVariable int activityID)
    {
        return activityService.endActivity(activityID);
    }

    @PutMapping(value = "/rename")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "重命名一个Activity",notes = "只需要activityID和activityName(新)\n权限")
    int renameActivity(@RequestBody Activity activity)
    {
        return activityService.renameActivity(activity);
    }

    @DeleteMapping("/{activityID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "删除一个Activity",notes = "教师权限")
    int deleteActivity(@PathVariable int activityID)
    {
       return activityService.deleteActivity(activityID); 
    }

}
