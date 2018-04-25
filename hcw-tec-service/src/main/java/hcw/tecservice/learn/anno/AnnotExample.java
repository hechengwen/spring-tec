package hcw.tecservice.learn.anno;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/4/19 11:19
 * Description:
 * Others:
 */
public class AnnotExample {

    @Override
    @MethodInfo(date = "2018/04/19",name = "hechengwen",value = "25岁")
    public String toString(){
        return "AnnotExample{}";
    }

    @Deprecated
    @MethodInfo(date = "2018-04-20",name = "10000",value = "10001")
    public void annoTest(){
        System.out.println(String.format("annoTest:%s%d","name=10000",10000));
    }
}
