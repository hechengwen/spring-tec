package hcw.tecservice.learn.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/3/20 16:49
 * Description:
 * Others:
 */
public class BeanToXml {
    /**
     * java对象转换为xml文件
     *
     * @param load java对象.Class
     * @return xml文件的String
     * @throws JAXBException
     */
    public static String beanToXml(Object obj, Class<?> load) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(load);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        StringWriter writer = new StringWriter();
        marshaller.marshal(obj, writer);
        return writer.toString();
    }

    public static void main(String[] args) throws Exception {
        List<String> hobby = new ArrayList<String>();
        hobby.add("篮球");
        hobby.add("音乐");
        hobby.add("乒乓球");
        List<Teacher> teachers = new ArrayList<Teacher>();
        StudentList lists = new StudentList();
        List<AA> list = new ArrayList<>();
        for (int i = 0; i <= 3; i++) {
            AA aa = new AA();
            BB bb = new BB();
            bb.setNnnnn("aaaaaaa" + i);
            bb.setVvvvv("bbbbbbb" + i);
            aa.setPage("000" + i);
            aa.setSdf(i);
            aa.setBb(bb);
            list.add(aa);
        }
        Teacher t1 = new Teacher("张老师", "男", 10001, "尖子班", hobby, list);
        Teacher t2 = new Teacher("李老师", "男", 10002, "普通班", hobby, list);
        Teacher t3 = new Teacher("王老师", "男", 10003, "实验班", hobby, list);
        teachers.add(t1);
        teachers.add(t2);
        teachers.add(t3);
        lists.setTeacher(teachers);
        String str = BeanToXml.beanToXml(lists, StudentList.class);
        String xmlPath = "D:\\testConfig.xml";
        BufferedWriter bfw = new BufferedWriter(new FileWriter(new File(xmlPath)));
        bfw.write(str);
        bfw.close();
        /*List<String> hobby = new ArrayList<String>();
        hobby.add("篮球");
        hobby.add("音乐");
        hobby.add("乒乓球");
        List<Student> studentList = new ArrayList<Student>();
        Student st = new Student("张三", "男", 10001, "尖子班", hobby);
        Student st1 = new Student("李四", "男", 10002, "普通班", hobby);
        Student st2 = new Student("莉莉", "女", 10003, "普通班", hobby);
        studentList.add(st);
        studentList.add(st1);
        studentList.add(st2);
        StudentList students = new StudentList();
        students.setStudents(studentList);
        String str = BeanToXml.beanToXml(students, StudentList.class);
        String xmlPath = "D:\\testConfig.xml";
        BufferedWriter bfw = new BufferedWriter(new FileWriter(new File(xmlPath)));
        bfw.write(str);
        bfw.close();*/
    }
}
