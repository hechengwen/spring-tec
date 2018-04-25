package hcw.tecservice.learn.java8;

import org.springframework.core.convert.converter.Converter;

import java.util.*;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/3/26 15:07
 * Description:
 * Others:
 */
public class Test {
    public static void main(String[] args) {
//        Formula formula = new Formula() {
//            @Override
//            public double claculate(int a) {
//                return sqrt(a);
//            }
//        };
//        System.out.println(formula.sqrt(16));
//        System.out.println(formula.claculate(100));
//
//        List<String> list = Arrays.asList("peter","anna","mike","xenia");
//
        String[] s = {"1","2","3"};
        List list = new ArrayList(s.length);
        Collections.addAll(list,s);

        List lista = Arrays.asList(s);
        list.addAll(lista);
        list.add("4");

        /*Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });*/

        /*Collections.sort(list,(String a,String b) -> {
            return a.compareTo(b);
        });*/

//        Collections.sort(list,(String a,String b) -> (a.compareTo(b)));
//        Collections.sort(list,(a,b) -> a.compareTo(b));


//        Collections.sort(list);
        System.out.println(list);
        System.out.println(lista);

//        Test test = new Test();
////        test.df();
//        Converter<String,String> converter = test::df;
//        String value = converter.convert("00000");
//        System.out.println(value);
    }


    public String df(String s){
        return s;
    }

    public static class sdf extends Thread{
        @Override
        public void run(){

        }
    }

}
