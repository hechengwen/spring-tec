package hcw.tecservice.threadpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/6/11 15:28
 * Description:
 * Others:
 */
@Component
public class TaskScheduled {

    private TaskScheduleService service = TaskScheduleService.getInstance();

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());


    public TaskScheduled(){
        logger.info("init TaskSheeduled!");
    }

    @Scheduled(cron="*/5 * * * * ?")
    public void execute(){
//        logger.info("TaskSheeduled每次执行一次定时任务……");
        service.next();
    }
}
