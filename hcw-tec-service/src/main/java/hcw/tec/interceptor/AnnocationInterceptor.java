package hcw.tec.interceptor;

import hcw.tec.annocation.LoginRequired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/1/16 11:10
 * Description:
 * Others:
 */
public class AnnocationInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        // 如果不是映射到方法直接通过
        if (!(o instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) o;
        Object bean = handlerMethod.getBean();
        Method method = handlerMethod.getMethod();
        LoginRequired loginRequired = bean.getClass().getAnnotation(LoginRequired.class); //类上有该标记
        LoginRequired methodAnnotation = method.getAnnotation(LoginRequired.class);// 方法上有该标记
        if (methodAnnotation != null ){
            String value = methodAnnotation.value();

            // 如果方法上有该注解，则判断session中用户是否登录。。。。

            return true;
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
