package hcw.tec.service.impl;

import hcw.tec.service.RemoteService;
/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/1/15 13:59
 * Description:
 * Others:
 */
public class RemoteServiceImpl implements RemoteService {

    @Override
    public String testDubbo(String str) {
        System.out.println("RemoteServiceImpl provider server start.....");
        return null;
    }
}
