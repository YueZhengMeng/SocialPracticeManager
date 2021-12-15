package com.shou.socialpracticemanager.config;

import com.shou.socialpracticemanager.security.NonePasswordEncoder;
import com.shou.socialpracticemanager.security.*;
import com.shou.socialpracticemanager.security.handler.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;//未登陆时返回 JSON 格式的数据给前端（否则为 html）

    @Autowired
    private LoginAuthenticationSuccessHandler loginAuthenticationSuccessHandler; //登录成功返回的 JSON 格式数据给前端（否则为 html）

    @Autowired
    private LoginAuthenticationFailureHandler loginAuthenticationFailureHandler; //登录失败返回的 JSON 格式数据给前端（否则为 html）

    @Autowired
    private LogoutAuthenticationSuccessHandler logoutAuthenticationSuccessHandler;//注销成功返回的 JSON 格式数据给前端（否则为 登录时的 html）

    @Autowired
    private AccessAuthenticationDeniedHandler accessAuthenticationDeniedHandler;//无权访问返回的 JSON 格式数据给前端（否则为 403 html 页面）

    @Autowired
    private UserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(jwtUserDetailsService)
                .passwordEncoder(new NonePasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors()
                .and()
                .csrf().disable();

        httpSecurity
                .authorizeRequests()
                .antMatchers("/v2/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/swagger-ui/**").permitAll()
                .antMatchers("/druid/**").permitAll()
                .antMatchers("/webjars/**", "/static/**").permitAll()
                .antMatchers("/api/user/register").permitAll()
                .antMatchers("/api/user/all").hasRole("admin")
                .antMatchers("/api/practice","/api/practice/end","/api/practice/rename").hasRole("teacher")
                .antMatchers("/api/activity","/api/activity/end","/api/activity/rename").hasRole("teacher")
                .anyRequest().authenticated();

        httpSecurity
                .formLogin()
                .loginProcessingUrl("/api/login")
                .successHandler(loginAuthenticationSuccessHandler) // 登录成功
                .failureHandler(loginAuthenticationFailureHandler) // 登录失败
                .permitAll();

        httpSecurity
                .logout()
                .logoutUrl("/api/logout")//这个接口没有用
                .logoutSuccessHandler(logoutAuthenticationSuccessHandler)
                .permitAll();

        httpSecurity
                .httpBasic()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint);

        httpSecurity
                .exceptionHandling()
                .accessDeniedHandler(accessAuthenticationDeniedHandler);

        httpSecurity
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
