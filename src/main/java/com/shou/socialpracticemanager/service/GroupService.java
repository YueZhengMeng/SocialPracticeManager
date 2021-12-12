package com.shou.socialpracticemanager.service;

import com.shou.socialpracticemanager.dao.GroupDao;
import com.shou.socialpracticemanager.dao.GroupParticipationDao;
import com.shou.socialpracticemanager.po.Group;
import com.shou.socialpracticemanager.po.GroupParticipation;
import com.shou.socialpracticemanager.security.JwtUserDetail;
import com.shou.socialpracticemanager.security.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GroupService {
    @Autowired
    GroupDao groupDao;

    @Autowired
    JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    GroupParticipationDao groupParticipationDao;

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
}
