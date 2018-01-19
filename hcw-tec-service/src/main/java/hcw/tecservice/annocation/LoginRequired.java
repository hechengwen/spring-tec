package hcw.tecservice.annocation;

import java.lang.annotation.*;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/1/16 11:13
 * Description:
 * Others:
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface LoginRequired {
    String value() default "";
}
