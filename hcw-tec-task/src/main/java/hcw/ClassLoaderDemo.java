package hcw;

import java.io.IOException;
import java.io.InputStream;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/1/23 15:21
 * Description:
 * Others:
 */
public class ClassLoaderDemo {

    public static void main(String[] args) throws IOException{
        // 用Class.getResourceAsStream() 时,路径应该是以"/"开头的
        InputStream inputStream = ClassLoaderDemo.class.getResourceAsStream("/a.properties");
        // 如果直接用ClassLoader的getResourceAsStream() 不用以"/"开头
        InputStream input = ClassLoaderDemo.class.getClassLoader().getResourceAsStream("a.properties");
        System.getProperties().load(inputStream);
        System.out.println(System.getProperty("name"));
    }

}
