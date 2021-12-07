package com.shou.socialpracticemanager.security.handler;


import com.alibaba.fastjson.JSON;
import com.shou.socialpracticemanager.Utils.JwtUtil;
import com.shou.socialpracticemanager.dto.JwtResponseMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


@Component
public class LoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        String jwtToken = JwtUtil.generateToken(authentication);
        Writer writer= new PrintWriter(new OutputStreamWriter(httpServletResponse.getOutputStream()),true);
        writer.write(JSON.toJSONString(JwtResponseMessage.LoginSuccess(ResultEnum.USER_LOGIN_SUCCESS,jwtToken)));
        writer.flush();
    }
}
