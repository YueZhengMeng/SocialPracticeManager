package com.shou.socialpracticemanager.service;

import com.shou.socialpracticemanager.dao.GroupDao;
import com.shou.socialpracticemanager.dao.GroupParticipationDao;
import com.shou.socialpracticemanager.dao.UserDao;
import com.shou.socialpracticemanager.dto.GroupMessage;
import com.shou.socialpracticemanager.po.Group;
import com.shou.socialpracticemanager.po.GroupParticipation;
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

    public List<GroupMessage> getAllGroup()
    {
        List<Group> allGroup = groupDao.selectAllGroup();
        List<Integer> allMyGroup = getMyGroup().stream().mapToInt(GroupMessage::getGroupID).boxed().toList();
        List<GroupMessage> allGroupMessage = new ArrayList<>();
        for (Group temp : allGroup)
        {
            if (allMyGroup.contains(temp.getGroupID()))
            {
                allGroupMessage.add(new GroupMessage(temp,1));
            }
            else {
                allGroupMessage.add(new GroupMessage(temp,0));
            }
        }
        return allGroupMessage;
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

    public List<Integer> getMyGroupID()
    {
        int userID = jwtUserDetailsService.getLoginUserId();
        return groupParticipationDao.selectGroupParticipationByUserID(userID)
                .stream().mapToInt(GroupParticipation::getGroupID).boxed().toList();
    }

    public List<GroupMessage> getMyGroup() {
        List<Integer> myGroupIDs = getMyGroupID();
        List<GroupMessage> groupMessage = new ArrayList<>();
        for (Integer groupID : myGroupIDs) {
            groupMessage.add(new GroupMessage(groupDao.selectGroupByID(groupID),1));
        }
        return groupMessage;
    }

    public List<GroupMessage> getGroupByPracticeID(int practiceID) {
        List<Group> groups = groupDao.selectGroupByPracticeID(practiceID);
        List<Integer> myGroupIDs = getMyGroupID();
        List<GroupMessage> groupMessage = new ArrayList<>();
        for (Group group : groups)
        {
            if (myGroupIDs.contains(group.getGroupID()))
            {
                groupMessage.add(new GroupMessage(group,1));
            }
            else {
                groupMessage.add(new GroupMessage(group,0));
            }
        }
        return groupMessage;
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

    public GroupMessage getTeacherGroup(int practiceID)
    {
        List<Group> groups = groupDao.selectGroupByPracticeID(practiceID);
        for (Group group : groups) {
            List<User> users = userService.getUserByGroupID(group.getGroupID());
            if (users != null && users.size() > 0 && users.get(0).getRole().equals("teacher")) {
                int userID = jwtUserDetailsService.getLoginUserId();
                List<Integer> userIDs = users.stream().mapToInt(User ::getUserID).boxed().toList();
                if (userIDs.contains(userID))
                {
                    return new GroupMessage(group,1);
                }
                else {
                    return new GroupMessage(group,0);
                }
            }
        }
        return null;
    }

    public int setGroupScore(Group group)
    {
        return groupDao.updateGroupScore(group);
    }
}
