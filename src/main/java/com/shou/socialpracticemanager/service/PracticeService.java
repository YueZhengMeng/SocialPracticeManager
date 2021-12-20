package com.shou.socialpracticemanager.service;

import com.shou.socialpracticemanager.dao.*;
import com.shou.socialpracticemanager.dto.PracticeMessage;
import com.shou.socialpracticemanager.po.*;
import com.shou.socialpracticemanager.security.JwtUserDetailsService;
import com.shou.socialpracticemanager.utils.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PracticeService {
    @Autowired
    private PracticeDao practiceDao;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private GroupParticipationDao groupParticipationDao;

    @Autowired
    private GroupDao groupDao;

    @Autowired
    ActivityDao activityDao;

    @Autowired
    ActivityParticipationDao activityParticipationDao;

    public List<PracticeMessage> getAllPractice()
    {
        List<Practice> allPractice = practiceDao.selectAllPractice();
        List<Integer> myPracticeID = getMyPractice().stream().mapToInt(PracticeMessage::getPracticeID).boxed().toList();
        List<PracticeMessage> allPracticeMessage = new ArrayList<>();
        for (Practice practice : allPractice)
        {
            if (myPracticeID.contains(practice.getPracticeID()))
            {
                allPracticeMessage.add(new PracticeMessage(practice,1));
            }
            else {
                allPracticeMessage.add(new PracticeMessage(practice,0));
            }
        }
        return allPracticeMessage;
    }

    public int creatPractice(String practiceName) {
        Practice practice = new Practice(practiceName);
        practiceDao.addPractice(practice);

        int practiceID = practiceDao.selectPracticeByNameAndStartTime(practice).getPracticeID();
        Group group = new Group(practiceName + "TeacherGroup", practiceID);
        groupDao.addGroup(group);

        int groupID = groupDao.selectGroupByNameAndPracticeID(practiceName + "TeacherGroup", practiceID).getGroupID();
        int userID = jwtUserDetailsService.getLoginUserId();
        GroupParticipation groupParticipation = new GroupParticipation(groupID, userID);
        return groupParticipationDao.addGroupParticipation(groupParticipation);

    }

    public List<PracticeMessage> getMyPractice() {
        int userID = jwtUserDetailsService.getLoginUserId();
        List<GroupParticipation> myGroupParticipation = groupParticipationDao.selectGroupParticipationByUserID(userID);
        List<Integer> myGroupID = myGroupParticipation.stream().mapToInt(GroupParticipation::getGroupID).boxed().toList();
        List<Integer> myPracticeID = new ArrayList<>();
        for (int groupID : myGroupID) {
            myPracticeID.add(groupDao.selectGroupByID(groupID).getPracticeID());
        }
        List<PracticeMessage> myPractice = new ArrayList<>();
        for (int practiceID : myPracticeID) {
            Practice temp = practiceDao.selectPracticeByID(practiceID);
            PracticeMessage practiceMessage= new PracticeMessage(temp,1);
            myPractice.add(practiceMessage);
        }
        return myPractice;
    }

    public int joinPractice(Group group)
    {
        groupDao.addGroup(group);
        int groupID = groupDao.selectGroupByNameAndPracticeID(group.getGroupName(), group.getPracticeID()).getGroupID();
        int userID = jwtUserDetailsService.getLoginUserId();
        GroupParticipation groupParticipation = new GroupParticipation(groupID, userID);
        return groupParticipationDao.addGroupParticipation(groupParticipation);
    }

    public int endPractice(int practiceID)
    {
        Practice practice = practiceDao.selectPracticeByID(practiceID);
        practice.setEndTime(DateTimeUtil.getSystemTime());
        List<Activity> activities = activityDao.selectActivityByPracticeID(practiceID);
        for (Activity activity : activities) {
            int activityID =activity.getActivityID();
            List<ActivityParticipation> activityParticipations = activityParticipationDao.selectActivityParticipationByActivityID(activityID);
            for (ActivityParticipation activityParticipation : activityParticipations) {
                activityParticipationDao.endActivityParticipation(activityParticipation.getActivityParticipationID());
            }
            activity.setEndTime(DateTimeUtil.getSystemTime());
            activityDao.endActivity(activity);
        }
        return practiceDao.endPractice(practice);
    }

    public int renamePractice(Practice practice) {
        return practiceDao.updatePractice(practice);
    }

    public int deletePractice(int practiceID) {
        return practiceDao.deletePractice(practiceID);
    }
}
