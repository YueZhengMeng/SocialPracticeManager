package com.shou.socialpracticemanager.security.handler;


import com.alibaba.fastjson.JSON;
import com.shou.socialpracticemanager.dto.JwtResponseMessage;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Component
public class AccessAuthenticationDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        Writer writer= new PrintWriter(new OutputStreamWriter(httpServletResponse.getOutputStream()),true);
        writer.write(JSON.toJSONString(JwtResponseMessage.result(ResultEnum.USER_NO_ACCESS)));
        writer.flush();
    }
}
