package com.shou.socialpracticemanager.service;

import com.shou.socialpracticemanager.dao.GroupDao;
import com.shou.socialpracticemanager.dao.GroupParticipationDao;
import com.shou.socialpracticemanager.dao.UserDao;
import com.shou.socialpracticemanager.po.Group;
import com.shou.socialpracticemanager.po.GroupParticipation;
import com.shou.socialpracticemanager.po.Practice;
import com.shou.socialpracticemanager.po.User;
import com.shou.socialpracticemanager.security.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GroupService {
    @Autowired
    GroupDao groupDao;

    @Autowired
    UserDao userDao;

    @Autowired
    JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    GroupParticipationDao groupParticipationDao;

    @Autowired
    UserService userService;

    public int renameGroup(Group group) {
        return groupDao.updateGroup(group);
    }

    public List<Group> getAllGroup()
    {
        return groupDao.selectAllGroup();
    }

    public int joinGroup(int groupID)
    {
        int userID = jwtUserDetailsService.getLoginUserId();
        GroupParticipation groupParticipation = new GroupParticipation(groupID, userID);
        return groupParticipationDao.addGroupParticipation(groupParticipation);
    }

    public int leaveGroup(int groupID)
    {
        int userID = jwtUserDetailsService.getLoginUserId();
        int i = groupParticipationDao.selectGroupParticipationByGroupID(groupID).size();
        if (i!=1) {
            return groupParticipationDao.deleteGroupParticipationByGroupIDAndUserID(groupID,userID);
        }
        else {
            return groupDao.deleteGroup(groupID);
        }
    }

    public List<Group> getMyGroup() {
        int userID = jwtUserDetailsService.getLoginUserId();
        return groupDao.selectGroupByUserID(userID);
    }

    public List<Group> getGroupByPracticeID(int practiceID) {
        return groupDao.selectGroupByPracticeID(practiceID);
    }

    public List<Group> getGroupByActivityID(int activityID) {
        List<GroupParticipation> groupParticipation = groupParticipationDao.selectGroupParticipationByActivityID(activityID);
        List<Integer> groupIDs = groupParticipation.stream().mapToInt(GroupParticipation::getGroupID).boxed().toList();
        List<Group> groups = new ArrayList<>();
        for (Integer groupID : groupIDs)
        {
            groups.add(groupDao.selectGroupByID(groupID));
        }
        return groups;
    }

    public Group getTeacherGroup(int practiceID)
    {
        List<Group> groups = groupDao.selectGroupByPracticeID(practiceID);
        for (Group group : groups) {
            List<User> users = userService.getUserByGroupID(group.getGroupID());
            if (users != null && users.size() > 0 && users.get(0).getRole().equals("teacher")) {
                return group;
            }
        }
        return null;
    }
}
