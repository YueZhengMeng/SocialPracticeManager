package com.shou.socialpracticemanager.service;

import com.shou.socialpracticemanager.dao.GroupParticipationDao;
import com.shou.socialpracticemanager.dao.UserDao;
import com.shou.socialpracticemanager.po.GroupParticipation;
import com.shou.socialpracticemanager.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    GroupParticipationDao groupParticipationDao;

    public List<User> getAllUser() {
        return userDao.selectAllUser();
    }

    public User getUserById(int userID) {
        return userDao.selectUserByID(userID);
    }

    public User getUserByName(String username) {
        return userDao.selectUserByName(username);
    }

    public List<User> getUserByGroupID(int groupID){
        List<GroupParticipation> groupParticipation = groupParticipationDao.selectGroupParticipationByGroupID(groupID);
        List<Integer> userIDs = groupParticipation.stream().mapToInt(GroupParticipation::getUserID).boxed().toList();
        List<User> users = new ArrayList<>();
        for (Integer userID : userIDs)
        {
            users.add(userDao.selectUserByID(userID));
        }
        return users;
    }

    public int registerUser(User user) {
        return userDao.addUser(user);
    }
}
