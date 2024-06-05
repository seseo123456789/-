package com.green.Team3.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class LoginFailHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String errorMsg = "";
        if(exception instanceof BadCredentialsException || exception instanceof InternalAuthenticationServiceException){
            errorMsg = "아이디 또는 비밀번호가 틀립니다.";
        }
        else if (exception instanceof UsernameNotFoundException){
            errorMsg = "존재하지 않는 사용자 ID입니다.";
        }
        else{
            errorMsg = "알수 없는 이유로 로그인 실패";
        }
        errorMsg = URLEncoder.encode(errorMsg, StandardCharsets.UTF_8);
        response.sendRedirect("/?errorMsg="+errorMsg);
    }
}
