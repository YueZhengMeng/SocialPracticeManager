package com.shou.socialpracticemanager.controller;

import com.shou.socialpracticemanager.po.Activity;
import com.shou.socialpracticemanager.po.Group;
import com.shou.socialpracticemanager.po.Practice;
import com.shou.socialpracticemanager.service.ActivityService;
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
    public List<Activity> getActivityByPracticeId(int practiceID) {
        return activityService.getActivityByPracticeId(practiceID);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public int createActivity(@RequestParam String activityName, @RequestParam int practiceID)
    {
        return activityService.createActivity(activityName, practiceID);
    }

    @GetMapping("/ByPracticeID/{practiceID}")
    @ResponseStatus(HttpStatus.OK)
    public List<Activity> getActivityByPracticeID(@PathVariable int practiceID)
    {
        return activityService.getActivityByPracticeID(practiceID);
    }

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.CREATED)
    public int joinActivity(@RequestParam int activityID, @RequestParam int practiceID)
    {
        return activityService.joinActivity(activityID, practiceID);
    }

    @PutMapping(value = "/end/{activityID}")
    @ResponseStatus(HttpStatus.CREATED)
    int endActivity(@PathVariable int activityID)
    {
        return activityService.endActivity(activityID);
    }

    @PostMapping(value = "/rename")
    @ResponseStatus(HttpStatus.CREATED)
    int renameActivity(@RequestBody Activity activity)
    {
        return activityService.renameActivity(activity);
    }

    @DeleteMapping("/{activityID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    int deleteActivity(@PathVariable int activityID)
    {
       return activityService.deleteActivity(activityID); 
    }

}
