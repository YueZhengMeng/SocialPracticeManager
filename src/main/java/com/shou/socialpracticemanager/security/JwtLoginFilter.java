package com.shou.socialpracticemanager.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shou.socialpracticemanager.po.User;
import com.shou.socialpracticemanager.security.handler.LoginAuthenticationFailureHandler;
import com.shou.socialpracticemanager.security.handler.LoginAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtLoginFilter extends OncePerRequestFilter {
    @Autowired
    JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    LoginAuthenticationSuccessHandler loginAuthenticationSuccessHandler;

    @Autowired
    LoginAuthenticationFailureHandler loginAuthenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if ("/api/login".equals(request.getRequestURI())) {
            if (request.getMethod().equals("post") || request.getMethod().equals("POST")) {
                User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
                JwtUserDetail jwtUserDetail = jwtUserDetailsService.loadUserByUsername(user.getUsername());
                if (jwtUserDetail != null) {
                    if (user.getPassword().equals(jwtUserDetail.getPassword())) {
                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                                jwtUserDetail, null, jwtUserDetail.getAuthorities());
                        usernamePasswordAuthenticationToken
                                .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                        loginAuthenticationSuccessHandler.onAuthenticationSuccess(request, response, usernamePasswordAuthenticationToken);
                    }
                    else {
                        loginAuthenticationFailureHandler.onAuthenticationFailure(request, response, new BadCredentialsException("password error"));
                    }
                } else {
                    loginAuthenticationFailureHandler.onAuthenticationFailure(request, response, new UsernameNotFoundException("user not found"));
                }
            }
            else {
                loginAuthenticationFailureHandler.onAuthenticationFailure(request, response, new AuthenticationServiceException("http method error"));
            }
            return;
        }
        else {
            filterChain.doFilter(request, response);
        }
    }
}
