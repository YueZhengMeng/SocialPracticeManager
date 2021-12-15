package com.shou.socialpracticemanager.security.handler;

import com.shou.socialpracticemanager.dto.JwtResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.setStatus(ResultEnum.USER_NEED_AUTHORITIES.getCode());
        Writer writer= new PrintWriter(new OutputStreamWriter(httpServletResponse.getOutputStream()),true);
        writer.write(JwtResponseMessage.deny(ResultEnum.USER_NEED_AUTHORITIES, e.getMessage()));
        writer.flush();
    }

}
