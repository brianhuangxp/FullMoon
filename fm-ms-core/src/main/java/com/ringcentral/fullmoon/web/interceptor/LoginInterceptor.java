package com.ringcentral.fullmoon.web.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by huangking on 14-1-7.
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //todo check login
        String url = request.getRequestURI();
        System.out.println("url" + url);
        System.out.println("preHandle" + handler.toString());
        return super.preHandle(request, response, handler);
    }
}
