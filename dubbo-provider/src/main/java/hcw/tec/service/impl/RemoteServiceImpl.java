package hcw.tec.service.impl;

import hcw.tec.service.RemoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/1/15 13:59
 * Description:
 * Others:
 */
@Service("remoteService")
public class RemoteServiceImpl implements RemoteService {

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public String testDubbo(String str) {
        logger.info("{},RemoteServiceImpl provider server start.....",str);
        String result = str + "调用 RemoteServiceImpl dubbo服务成功......";

        return result;
    }
}
