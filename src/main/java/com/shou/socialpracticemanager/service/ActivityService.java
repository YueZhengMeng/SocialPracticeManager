package com.shou.socialpracticemanager.service;


import com.shou.socialpracticemanager.dao.ActivityDao;
import com.shou.socialpracticemanager.dao.ActivityParticipationDao;
import com.shou.socialpracticemanager.dao.GroupDao;
import com.shou.socialpracticemanager.dao.GroupParticipationDao;
import com.shou.socialpracticemanager.dto.ActivityMessage;
import com.shou.socialpracticemanager.po.Activity;
import com.shou.socialpracticemanager.po.ActivityParticipation;
import com.shou.socialpracticemanager.po.Group;
import com.shou.socialpracticemanager.po.GroupParticipation;
import com.shou.socialpracticemanager.security.JwtUserDetailsService;
import com.shou.socialpracticemanager.utils.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ActivityService {
    @Autowired
    ActivityDao activityDao;

    @Autowired
    ActivityParticipationDao activityParticipationDao;

    @Autowired
    JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    GroupParticipationDao groupParticipationDao;

    @Autowired
    GroupDao groupDao;

    public List<ActivityMessage> getActivityByPracticeID(int practiceID) {
        List<ActivityMessage> allActivityMessage = new ArrayList<>();
        List<Integer> myActivityID = getMyActivityID();
        List<Activity> activities = activityDao.selectActivityByPracticeID(practiceID);
        for (Activity activity : activities) {
            if (myActivityID.contains(activity.getActivityID()))
            {
                allActivityMessage.add(new ActivityMessage(activity,1));
            }
            else {
                allActivityMessage.add(new ActivityMessage(activity,0));
            }
        }
        return allActivityMessage;
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
        List<ActivityParticipation> activityParticipations = activityParticipationDao.selectActivityParticipationByActivityID(activityID);
        for (ActivityParticipation activityParticipation : activityParticipations) {
            activityParticipationDao.endActivityParticipation(activityParticipation.getActivityParticipationID());
        }
        return activityDao.endActivity(activity);
    }

    public int renameActivity(Activity activity) {
        return activityDao.updateActivity(activity);
    }

    public int deleteActivity(int activityID) {
        return activityDao.deleteActivity(activityID);
    }

    public ActivityParticipation getActivityState(ActivityParticipation activityParticipation) {
        return activityParticipationDao.deleteActivityParticipationByActivityIDAndGroupID(activityParticipation);
    }

    public List<Integer> getMyActivityID()
    {
        int userID = jwtUserDetailsService.getLoginUserId();
        List<Integer> myGroupID = groupParticipationDao.selectGroupParticipationByUserID(userID)
                .stream().mapToInt(GroupParticipation::getGroupID).boxed().toList();
        List<Integer> myActivityID = new ArrayList<>();
        for (int groupID: myGroupID) {
            List<Integer> activityIDs = activityParticipationDao.selectActivityParticipationByGroupID(groupID)
                    .stream().mapToInt(ActivityParticipation::getActivityID).boxed().toList();
            myActivityID.addAll(activityIDs);
        }
        return myActivityID;
    }

    public List<ActivityMessage> getMyActivity()
    {
        List<ActivityMessage> myActivity = new ArrayList<>();
        List<Integer> myActivityID = getMyActivityID();
        for (int activityID : myActivityID)
        {
            Activity activity = activityDao.selectActivityByID(activityID);
            myActivity.add(new ActivityMessage(activity,1));
        }
        return myActivity;
    }

    public List<ActivityMessage> getAllActivity() {
        List<ActivityMessage> allActivityMessage = new ArrayList<>();
        List<Integer> myActivityID = getMyActivityID();
        List<Activity> activities = activityDao.selectAllActivity();
        for (Activity activity : activities) {
            if (myActivityID.contains(activity.getActivityID()))
            {
                allActivityMessage.add(new ActivityMessage(activity,1));
            }
            else {
                allActivityMessage.add(new ActivityMessage(activity,0));
            }
        }
        return allActivityMessage;
    }
}
