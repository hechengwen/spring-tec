package hcw.tec.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2017/12/20 10:13
 * Description:
 * Others:
 */
@Component("springTaskTest")
@Slf4j
public class SpringTaskTest {

    @Scheduled(cron = "0/1 * * * * ?")
    public void test(){
        log.info("{},定时任务扫描中.....",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date()));
        try {
            Thread.sleep(1000*10);
        } catch (InterruptedException i) {

        }
    }

    @Scheduled(cron = "0/2 * * * * ?")
    public void everyday(){
        log.info("{},下午13点17分有一个定时任务.....",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
}
