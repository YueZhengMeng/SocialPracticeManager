package com.shou.socialpracticemanager.security.handler;

import com.shou.socialpracticemanager.dto.JwtResponseMessage;
import com.shou.socialpracticemanager.dto.ResultEnum;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;

@Component
public class LogoutAuthenticationSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.setStatus(ResultEnum.USER_LOGOUT_SUCCESS.getCode());
        Writer writer= new PrintWriter(new OutputStreamWriter(httpServletResponse.getOutputStream()),true);
        writer.write(JwtResponseMessage.result(ResultEnum.USER_LOGOUT_SUCCESS));
        writer.flush();
    }

}
