package hcw.tec.controller;

import hcw.tec.globalexception.GlobalException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/1/16 15:15
 * Description:
 * Others:
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("exception", e.getMessage());
        mav.addObject("desc","全局异常都由我来处理");
        mav.addObject("url", req.getRequestURL());
        mav.addObject("contextUrl",req.getRequestURI());
        return mav;
    }

    @ExceptionHandler(value = GlobalException.class)
    public ModelAndView myException(HttpServletRequest req, GlobalException e) throws Exception {
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("exception", e.getMessage());
//        mav.addObject("desc","全局异常都由我来处理");
        mav.addObject("url", req.getRequestURL());
        mav.addObject("contextUrl",req.getRequestURI());
        return mav;
    }

    @ExceptionHandler(value = Exception.class)
    public ModelAndView exception(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("exception", e.getMessage());
//        mav.addObject("desc","全局异常都由我来处理");
//        mav.addObject("url", req.getRequestURL());
//        mav.addObject("contextUrl",req.getRequestURI());
        return mav;
    }
}
