package hcw.tec.task.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/1/17 16:37
 * Description:
 * Others:
 */
public class QuartzJobFactory implements Job{

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Object job = jobExecutionContext.getJobDetail().getJobDataMap().get("scheduleJob");
        logger.info("[{}],JobDetail:{} - quartz runing .....",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date()),job);
    }

    public static void main(String[] args) {
        String jobName= "job1";
        QuartzManager.addJob(jobName,QuartzJobFactory.class,"0/10 * * * * ?",jobName);
    }
}
