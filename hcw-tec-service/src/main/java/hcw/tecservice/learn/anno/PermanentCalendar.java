package hcw.tecservice.learn.anno;

import java.util.Scanner;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/4/19 17:07
 * Description:万年历
 * Others:
 */
public class PermanentCalendar {

    static boolean LeapYear(int year) { //判断闰年
        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) { //是闰年
            return true;
        } else {
            return false;
        }
    }

    static final int N = 1900; //用N表示起始年份

    public static void main(String[] args) {
        int n = 1;//1900年1月1日 是星期一
        int month[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; //用来存储月份的天数
        int year[] = new int[1001];  //用来存储每年1月1日是星期几
        year[0] = n;
        for (int i = 1; i < year.length; i++) {
            int days = 365;
            if (LeapYear(i + N - 1)) {//如果它的前一年是闰年则需要加366
                days = 366;
                year[i] = (year[i - 1] + days) % 7;
            } else {
                year[i] = (year[i - 1] + days) % 7;
            }
        }
        /*
        for(int i=0;i<10;i++){
            System.out.println(year[i]);
        }
        */
        int Month, Year;
        int[] Months = {1,2,3,4,5,6,7,8,9,10,11,12};
        int[] Years = {2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2018};
        for (int a = 0 ;a<=Years.length-1;a++) {
            for (int b = 0 ;b<=Months.length-1;b++) {
                Year = Years[a];
                Month = Months[b];
                System.out.println(Year+"年"+Month+"月");
                System.out.println("星期日   " + "星期一   " + "星期二   " + "星期三   " + "星期四   " + "星期五   " + "星期六   ");
                System.out.println();
                if (LeapYear(Year)) { //如果是闰年,2月改为29号
                    month[2] = 29;
                }
                int day = 0; //用来记录当前月的一号是今年的第几天
                for (int i = 1; i < Month; i++) {
                    day = day + month[i];
                }

                day = (year[Year - N] + day) % 7;
                for (int i = 0; i < 7; i++) {//输出控制
                    if (day == i) {
                        System.out.print("   " + 1 + "    ");
                        if (day == 6) {
                            System.out.println();
                        }
                        break;
                    } else {
                        System.out.print("      ");
                    }
                }
                for (int i = 2; i <= month[Month]; i++) {
                    if (i < 10) {
                        System.out.print("   " + i + "    ");
                    } else {
                        System.out.print("  " + i + "    ");
                    }
                    if ((day + i - 1) % 7 == 6) {
                        System.out.println();
                    }
                }
                System.out.println();
            }
        }
        /*Scanner in = new Scanner(System.in);
        System.out.println("请输入年份在(1900~2900之间):");
        Year = in.nextInt();
        if (Year > 2900 || Year < 1900) {
            System.out.println("输入年份不合法,请重新输入!");
            return;
        }*/
        //System.out.println(year[Year-1970]);
        /*System.out.println("请输入月份(1~12之间):");
        Month = in.nextInt();
        if (Month > 12 || Month < 1) {
            System.out.println("输入月份不合法,请重新输入!");
            return;
        }*/

    }
}
