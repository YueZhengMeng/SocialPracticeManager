package com.shou.socialpracticemanager.controller;

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
    @ApiOperation(value = "获取所有Activity信息",notes = "管理员用")
    public List<Activity> getActivityByPracticeId(int practiceID) {
        return activityService.getActivityByPracticeId(practiceID);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "新建Activity",notes = "教师用\n activityName和practiceID 必须\n教师用")
    public int createActivity(@RequestBody Activity activity)
    {
        return activityService.createActivity(activity);
    }

    @GetMapping("/ByPracticeID/{practiceID}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "获取某个Practice拥有的所有Activity信息",notes = "通用")
    public List<Activity> getActivityByPracticeID(@PathVariable int practiceID)
    {
        return activityService.getActivityByPracticeID(practiceID);
    }

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "某个Group加入某个Activity",notes = "activityID和groupID 必须\n通用")
    public int joinActivity(@RequestBody ActivityParticipation activityParticipation)
    {
        return activityService.joinActivity(activityParticipation);
    }

    @PostMapping("/state")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "某个Group完成某个Activity的情况",notes = "activityID和groupID 必须\n通用")
    public ActivityParticipation getActivityState(@RequestBody ActivityParticipation activityParticipation)
    {
        return activityService.getActivityState(activityParticipation);
    }

    @PutMapping(value = "/end/{activityID}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "结束一个Activity",notes = "教师用")
    int endActivity(@PathVariable int activityID)
    {
        return activityService.endActivity(activityID);
    }

    @PostMapping(value = "/rename")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "重命名一个Activity",notes = "教师用\n activityID和activityName(新) 必须")
    int renameActivity(@RequestBody Activity activity)
    {
        return activityService.renameActivity(activity);
    }

    @DeleteMapping("/{activityID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "删除一个Activity",notes = "教师用")
    int deleteActivity(@PathVariable int activityID)
    {
       return activityService.deleteActivity(activityID); 
    }

}
