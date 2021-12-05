package com.shou.socialpracticemanager.Utils;


import com.alibaba.fastjson.JSON;
import com.shou.socialpracticemanager.dto.JwtResponseMessage;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.getWriter().write(JSON.toJSONString(JwtResponseMessage.result(ResultEnum.USER_LOGIN_FAILED)));
        httpServletResponse.reset();
    }

}