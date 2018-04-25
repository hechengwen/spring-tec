package hcw.tecservice.learn.anno;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/4/19 11:18
 * Description:
 * Others:
 */
public class TestAnno {
    public static void main(String[] args) {

        for (Method method : AnnotExample.class.getMethods()) {
            if (method.isAnnotationPresent(MethodInfo.class)) {
                for (Annotation annotation : method.getAnnotations()) {
                    System.out.println(annotation + " in method:" +method);
                }

                MethodInfo methodInfo = method.getAnnotation(MethodInfo.class);
                System.out.println(String.format("data:%s,name:%s,value:%s",methodInfo.date(),methodInfo.name(),methodInfo.value()));
                System.out.println();
            }
        }
    }
}
