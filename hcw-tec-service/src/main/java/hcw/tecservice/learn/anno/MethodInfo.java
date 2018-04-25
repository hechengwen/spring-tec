package hcw.tecservice.learn.anno;

import java.lang.annotation.*;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/4/19 11:17
 * Description:
 * Others:
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface MethodInfo {
    String name() default "";
    String value() default "";
    String date();
}
