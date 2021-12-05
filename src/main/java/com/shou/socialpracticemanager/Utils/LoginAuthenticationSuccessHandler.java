package com.shou.socialpracticemanager.Utils;


import com.alibaba.fastjson.JSON;
import com.shou.socialpracticemanager.dto.JwtResponseMessage;
import com.shou.socialpracticemanager.po.User;
import com.shou.socialpracticemanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class LoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        String jwtToken = userService.generateToken(authentication);
        httpServletResponse.getWriter().write(JSON.toJSONString(JwtResponseMessage.LoginSuccess(ResultEnum.USER_LOGIN_SUCCESS,jwtToken)));
        httpServletResponse.reset();
    }
}
