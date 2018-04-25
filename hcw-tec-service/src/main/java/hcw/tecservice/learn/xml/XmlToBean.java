package hcw.tecservice.learn.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/3/20 17:17
 * Description:
 * Others:
 */
public class XmlToBean {
    /**
     * xml文件配置转换为对象
     *
     * @param xmlPath xml文件路径
     * @param load    java对象.Class
     * @return java对象
     * @throws JAXBException
     * @throws IOException
     */
    public static Object xmlToBean(String xmlPath, Class<?> load) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(load);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Object object = unmarshaller.unmarshal(new File(xmlPath));
        return object;
    }

    public static void main(String[] args) throws Exception {
        /*String xmlPath = "D:/testConfig.xml";

        StudentList studentList = (StudentList) XmlToBean.xmlToBean(xmlPath, StudentList.class);

        for (Student student : studentList.getStudents()) {
            System.out.print("name:" + student.getName() + ", sex:" + student.getSex() + ", number:" + student.getNumber() + ", className:" + student.getClassName());
            for (String s : student.getHobby()) {
                System.out.print(" " + s + " ");
            }
            System.out.println();
        }*/

        System.out.println("getclass().getSuperclass() : " + B.class.getSuperclass()); // 返回直接继承的父类（由于编译擦除，没有显示泛型参数）
        System.out.println("getclass().getSuperclass() : " + B.class.getGenericSuperclass()); // 返回直接继承的父类（包含泛型参数）
        System.out.println("getclass().getSuperclass().getDeclaredFields() : " + B.class.getSuperclass().getDeclaredFields());//获得某个类的所有声明的字段，即包括public、private和proteced，但是不包括父类的申明字段。
        System.out.println("getclass().getSuperclass().getFields() : " + B.class.getSuperclass().getFields());//获得某个类的所有的公共（public）的字段，包括父类中的字段。
        for (Field field : B.class.getSuperclass().getDeclaredFields()) {
            System.out.println("DeclaredField : "+field.getName());
            System.out.println("获取字段的修饰符 : "+field.getModifiers());//获取字段的修饰符
            System.out.println("获取指定对象中此字段的值 : "+ field.get(new A()));//user可以看做是从数据库中查找出来的对象
        }

        System.out.println();
        for (Field field : B.class.getFields()) {
            System.out.println("获取字段的名称 : "+field.getName());//获取字段的名称
            System.out.println("获取字段的修饰符 : "+field.getModifiers());//获取字段的修饰符
            System.out.println("获取字段的声明类型 : "+field.getType());//获取字段的声明类型

            System.out.println("获取指定对象中此字段的值 : "+ field.get(new B()));//user可以看做是从数据库中查找出来的对象
        }
    }

    static class A<T> {
        public String id = "12306";
        String name;
        protected String hobby;
    }

    static class B extends A<XmlToBean> {
        public Integer age;
        public String s;
    }
}
