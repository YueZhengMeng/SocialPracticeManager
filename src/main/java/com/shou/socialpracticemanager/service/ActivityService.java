package com.shou.socialpracticemanager.service;


import com.shou.socialpracticemanager.dao.ActivityDao;
import com.shou.socialpracticemanager.dao.ActivityParticipationDao;
import com.shou.socialpracticemanager.po.Activity;
import com.shou.socialpracticemanager.po.ActivityParticipation;
import com.shou.socialpracticemanager.po.Practice;
import com.shou.socialpracticemanager.security.JwtUserDetailsService;
import com.shou.socialpracticemanager.utils.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ActivityService {
    @Autowired
    ActivityDao activityDao;

    @Autowired
    ActivityParticipationDao activityParticipationDao;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    public List<Activity> getActivityByPracticeID(int practiceID) {
        return activityDao.selectActivityByPracticeID(practiceID);
    }

    public List<Activity> getActivityByPracticeId(int practiceID) {
        return activityDao.selectActivityByPracticeID(practiceID);
    }


    public int createActivity(Activity activity) {
        Activity temp = new Activity(activity.getActivityName(),activity.getPracticeID());
        return activityDao.addActivity(temp);
    }

    public int joinActivity(ActivityParticipation activityParticipation)
    {
        ActivityParticipation temp = new ActivityParticipation(activityParticipation.getActivityID(),activityParticipation.getGroupID());
        return activityParticipationDao.addActivityParticipation(temp);
    }

    public int endActivity(int activityID)
    {
        Activity activity = activityDao.selectActivityByID(activityID);
        activity.setEndTime(DateTimeUtil.getSystemTime());
        return activityDao.endActivity(activity);
    }

    public int renameActivity(Activity activity) {
        return activityDao.updateActivity(activity);
    }

    public int deleteActivity(int activityID) {
        return activityDao.deleteActivity(activityID);
    }
}
