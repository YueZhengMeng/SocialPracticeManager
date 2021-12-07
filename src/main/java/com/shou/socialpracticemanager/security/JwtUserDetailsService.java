package com.shou.socialpracticemanager.security;

import com.shou.socialpracticemanager.dao.UserDao;
import com.shou.socialpracticemanager.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user= userDao.selectUserByName(username);
        if (user!=null) {
            String role = user.getRole();
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
            return new JwtUserDetail(user.getUserid(),user.getUsername(), user.getPassword(),authorities);
        } else {
            throw new UsernameNotFoundException("该用户名不存在: " + username);
        }
    }

}