package hcw.tecservice.learn.string;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/3/26 10:43
 * Description:
 * Others:
 */
public class StringTest {
    public static void main(String[] args) {
        String s1 = "ab"+"cd";
        String s2 = "abcd";
        System.out.println("s1==s2 : "+ (s1==s2));

        String s3 = "ab";
        String s4 = "cd";
        // 运行期JVM首先会在堆中创建一个StringBuilder类，同时用s3指向的拘留字符串对象完成初始化，然后调用append方法完成对s4所指向的拘留字符串的合并，
        // 接着调用StringBuilder的toString()方法在堆中创建一个String对象，最后将刚生成的String对象的堆地址存放在局部变量s5中。
        String s5 = s3+s4; // 总的来说，这段代码就是创建了一个string对象，存放在堆内存中，s2存放在常量池中，地址不一样
        System.out.println("s5==s2 : "+(s5==s2));

        /*
         * JAVA编译器对string + 基本类型/常量 是当成常量表达式直接求值来优化的
         * 运行期的两个string相加，会产生新的对象的，存储在堆(heap)中
         *
         */
        String s6 = "b"; // 变量
        String s7 = "a" +s6;
        String s8 = "ab";
        System.out.println("s7==s8 : "+(s7==s8));

        final String s9 = "b"; // 常量
        String s10 = "a" + s9;
        System.out.println("s10==s8 : "+(s10==s8));
        try {
            readNIO();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readNIO() throws Exception{
        FileInputStream fileInputStream = new FileInputStream(new File("D:\\log(1).txt"));
        FileOutputStream fileOutputStream = new FileOutputStream(new File("D:\\a.log"));
        FileChannel fileChannelIn = fileInputStream.getChannel();
        FileChannel fileChannelOut = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);// 生成一个偏移量为0，容量和最大容量都为1024的bytebuffer
        while (true) {
            byteBuffer.clear(); //重设缓冲区，使它可以接受读入的数据。
            int r = fileChannelIn.read(byteBuffer);
            if (r == -1) break;
            byteBuffer.flip(); //让缓冲区可以将新读入的数据写入另一个通道
            fileChannelOut.write(byteBuffer);

        }
    }
}
