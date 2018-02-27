package hcw.tec.test;

import java.util.*;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/1/30 15:37
 * Description:
 * Others:
 */
public class Test {
    public static void main(String[] args) {
        addAll();
    }

    public static void testMap() {
        T t = new T();
        System.out.println(t.hashCode());
        Map<String, String> map = new HashMap<String, String>();

        for (int i = 0; i <= 10; i++) {
            map.put(String.valueOf(i), i + "");
        }

        // 将map中所有key-value存入map1
        Map<String, String> map1 = new HashMap<String, String>(map);
        map1.put("name", "map1");

        List list = new ArrayList();
        for (int i = 0; i <= 10; i++) {
            list.add(i);
        }
        int q = (Integer) list.set(1, "woaini");
//        list.add(1,"daiwang");

        list.remove(5);
        list.remove("");
        list.clear();

        List list1 = new ArrayList();
        for (int i = 0; i <= 10; i++) {
            list1.add(i);
        }
        Iterator iterator = list1.iterator();
        while (iterator.hasNext()) {
            int a = (Integer) iterator.next();
            if (a == 5) {
                iterator.remove();
            }
        }


    }

    public static void addAll() {
        List a = new ArrayList();
        for (int i = 0; i < 5; i++) {
            a.add(i);
        }

        List b = new ArrayList();
        for (int j = 6; j < 10; j++) {
            b.add(j);
        }

        a.addAll(2,b);

        List linkList = new LinkedList();
        linkList.add("");

    }


    public static void setDesc(){
        Set set = new HashSet();
    }

    static class T {
    }
}
