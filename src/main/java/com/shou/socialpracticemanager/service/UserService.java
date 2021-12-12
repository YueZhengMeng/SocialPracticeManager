package com.shou.socialpracticemanager.service;

import com.shou.socialpracticemanager.dao.UserDao;
import com.shou.socialpracticemanager.po.User;
import com.shou.socialpracticemanager.security.JwtUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<User> getAllUser() {
        return userDao.selectAllUser();
    }

    User getUserById(String userID) {
        return userDao.selectUserByID(userID);
    }

    public User getUserByName(String username) {
        return userDao.selectUserByName(username);
    }
}
