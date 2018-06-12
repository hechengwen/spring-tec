package hcw.tecservice.learn.hashmap;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/5/15 10:19
 * Description:
 * Others:
 */
public class Sort {

    public static void main(String[] args) {
        int[] array = new int[10];
        for (int k = 0; k < array.length; k++) {
            array[k] = (int) (Math.random() * 100);
        }
        System.out.println("选择排序前：" + java.util.Arrays.toString(array));
        selectSort(array);
        fora();
    }

    /**
     * 选择排序
     *
     * @param array
     */
    public static void selectSort(int[] array) {
        System.out.println("开始排序");
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    int x = array[i];
                    array[i] = array[j];
                    array[j] = x;
                }
            }
            System.out.println("选择排序前：" + java.util.Arrays.toString(array));
        }
    }

    /**
     * @param array
     */
    public static void insertSort(int[] array) {
        System.out.println("开始排序");
    }


    public static void fora() {
        int count = 0;
        a:for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i == 5) {
                    break a;
                }
                System.out.println("i = " + i + ", j = " + j);
                count++;
            }
        }
        System.out.println("总次数 = " + count);
    }
}
