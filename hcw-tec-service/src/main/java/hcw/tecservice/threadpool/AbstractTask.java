package hcw.tecservice.threadpool;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/6/11 15:25
 * Description:
 * Others:
 */
@Data
public abstract class AbstractTask implements Runnable {

    private int cycNum;
    private int index;
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void run() {

        try {
            doProcess();
        } catch (Exception e) {
            logger.error("任务执行出错", e);
            //防御性容错！
        }

    }


    /**
     * 执行任务的方法
     */
    public abstract void doProcess();
}
