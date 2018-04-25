package hcw.tecservice.learn.java8;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/3/26 15:04
 * Description:
 * Others:
 */
public interface Formula {
    double claculate(int a);

    default double sqrt(int a) {
        return Math.sqrt(a);
    }
}
