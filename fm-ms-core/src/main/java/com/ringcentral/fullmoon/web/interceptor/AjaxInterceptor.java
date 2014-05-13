package com.ringcentral.fullmoon.web.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by huangking on 14-1-7.
 */
public class AjaxInterceptor extends HandlerInterceptorAdapter {
    private long startTime;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("AjaxInterceptor||preHandle");
        startTime = System.currentTimeMillis();
        return super.preHandle(request, response, handler);
    }

//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        long spend = System.currentTimeMillis() - startTime;
//        System.out.println("afterCompletion++" + spend);
//        Status status = new Status("1", "success");
//        AjaxResponseVo resVo = new AjaxResponseVo(status, handler, spend);
//        System.out.println("afterCompletion++" + resVo);
//        super.postHandle(request, response, resVo, modelAndView);
//    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        try {
            super.afterCompletion(request, response, handler, ex);
        } catch (Exception e) {
            System.out.println("Exception++" + e.getMessage());
        }

    }

}
