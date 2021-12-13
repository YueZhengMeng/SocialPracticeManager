package com.shou.socialpracticemanager.service;

import com.shou.socialpracticemanager.dao.GroupDao;
import com.shou.socialpracticemanager.dao.GroupParticipationDao;
import com.shou.socialpracticemanager.dao.PracticeDao;
import com.shou.socialpracticemanager.po.Group;
import com.shou.socialpracticemanager.po.GroupParticipation;
import com.shou.socialpracticemanager.po.Practice;
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

    public List<Practice> getAllPractice()
    {
        return practiceDao.selectAllPractice();
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

    public List<Practice> getMyPractice() {
        int userID = jwtUserDetailsService.getLoginUserId();
        List<GroupParticipation> myGroupParticipation = groupParticipationDao.selectGroupParticipationByUserID(userID);
        List<Integer> myGroupID = myGroupParticipation.stream().mapToInt(GroupParticipation::getGroupID).boxed().toList();
        List<Integer> myPracticeID = new ArrayList<>();
        for (int groupID : myGroupID) {
            myPracticeID.add(groupDao.selectGroupByID(groupID).getPracticeID());
        }
        List<Practice> myPractice = new ArrayList<>();
        for (int practiceID : myPracticeID) {
            myPractice.add(practiceDao.selectPracticeByID(practiceID));
        }
        return myPractice;
    }

    public int joinPractice(int practiceID,String groupName)
    {
        Group group = new Group(groupName, practiceID);
        groupDao.addGroup(group);

        int groupID = groupDao.selectGroupByNameAndPracticeID(groupName, practiceID).getGroupID();
        int userID = jwtUserDetailsService.getLoginUserId();
        GroupParticipation groupParticipation = new GroupParticipation(groupID, userID);
        return groupParticipationDao.addGroupParticipation(groupParticipation);
    }

    public int endPractice(int practiceID)
    {
        Practice practice = practiceDao.selectPracticeByID(practiceID);
        practice.setEndTime(DateTimeUtil.getSystemTime());
        return practiceDao.endPractice(practice);
    }

    public int renamePractice(Practice practice) {
        return practiceDao.updatePractice(practice);
    }

    public int deletePractice(int practiceID) {
        return practiceDao.deletePractice(practiceID);
    }
}
