package com.shou.socialpracticemanager.service;

import com.shou.socialpracticemanager.Utils.JwtUtil;
import com.shou.socialpracticemanager.dao.UserDao;
import com.shou.socialpracticemanager.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public String generateToken(Authentication authentication)
    {
        String username = ((UserDetails) (authentication.getPrincipal())).getUsername();
        int userid = getUserByName(username).getUserid();
        Map<String, String> claims = new HashMap<>();
        claims.put("userid", String.valueOf(userid));
        claims.put("username",username);
        return JwtUtil.doGenerateToken(claims);
    }

    public List<User> getAllUser() {
        return userDao.selectAllUser();
    }

    User getUserById(String userid) {
        return userDao.selectUserById(userid);
    }

    public User getUserByName(String username) {
        return userDao.selectUserByName(username);
    }

    public String getLoginUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            if (authentication instanceof AnonymousAuthenticationToken) {
                return null;
            }
            if (authentication instanceof UsernamePasswordAuthenticationToken) {
                return ((UserDetails) (authentication.getPrincipal())).getUsername();
            }
        }
        return null;
    }

    public int getLoginUserId() {
        return userDao.selectUserByName(getLoginUserName()).getUserid();
    }
}
