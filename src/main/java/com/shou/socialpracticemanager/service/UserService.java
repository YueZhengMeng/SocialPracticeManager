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

    User getUserById(String userid) {
        return userDao.selectUserById(userid);
    }

    public User getUserByName(String username) {
        return userDao.selectUserByName(username);
    }

    public JwtUserDetail getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            if (authentication instanceof AnonymousAuthenticationToken) {
                return null;
            }
            if (authentication instanceof UsernamePasswordAuthenticationToken) {
                return (JwtUserDetail) (authentication.getPrincipal());
            }
        }
        return null;
    }

    public int getLoginUserId() {
        return getLoginUser().getUserid();
    }

    public String getLoginUserName() {
        return getLoginUser().getUsername();
    }
}
