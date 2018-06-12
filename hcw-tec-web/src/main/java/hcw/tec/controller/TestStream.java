package hcw.tec.controller;

import lombok.Data;

import java.io.*;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/3/19 10:39
 * Description:
 * Others:
 */
public class TestStream {

    public static void main(String[] args) throws Exception {
//        File file = new File("D:\\stream.properties");
//        objectOutput(file);
//        objectInput(file);

        PrintStream ps = new PrintStream(new FileOutputStream(new File("D:" + File.separator + "stream.properties")));
        /*String name = "小何";
        int age = 23;
        float score = 990.356f;
        char sex = 'M';
        ps.printf("姓名：%s;年龄：%d;分数：%f；性别：%c",name,age,score,sex);*/

//        ps.close();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            if ("over".equalsIgnoreCase(line)) {
                break;
            }
            ps.println(line.toUpperCase());
        }
        ps.close();
    }

    //序列化
    public static byte[] serialize(Object obj) {
        ObjectOutputStream objectOutputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

            objectOutputStream.writeObject(obj);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {

        }
        return null;
    }

    //反序列化
    public static Object unserizlize(byte[] byt) {
        ObjectInputStream objectInputStream = null;
        ByteArrayInputStream byteArrayInputStream = null;
        try {
            byteArrayInputStream = new ByteArrayInputStream(byt);
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            return objectInputStream.readObject();
        } catch (Exception e) {

        }
        return null;
    }

    private static void objectOutput(File file) throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(new Student(15, "linda", "rich"));
        objectOutputStream.close();
    }

    private static void objectInput(File file) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Student student = (Student) objectInputStream.readObject();
        System.out.println("id : " + student.getId() + ", name : " + student.getName() + ", value : " + student.getValue());
        objectInputStream.close();
    }

    /*
    * 如果你不想将某些信息存入到磁盘 就可以同过transient关键字修饰成员变量
    * transient修饰属性，表示暂时的，则这个属性不会被写入磁盘,不会被序列化的
    */
    @Data
    static class Student implements Serializable {
        private static final long serialVersionUID = -1236976413254254L;
        private int id;
        private String name;
        transient String value;

        public Student(int id, String name, String value) {
            this.id = id;
            this.name = name;
            this.value = value;
        }
    }
}
