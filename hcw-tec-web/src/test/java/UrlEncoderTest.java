import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/3/8 10:08
 * Description:
 * Others:
 */
public class UrlEncoderTest {
    public static void main(String[] args) throws Exception{
        String s = URLEncoder.encode("{\"order_name\":\"OrderName:9A1520419718443\",\"order_no\":\"15204150188610008\"}","iso-8859-1");
        String ss = URLEncoder.encode("{\"order_name\":\"OrderName:9A1520419718443\",\"order_no\":\"15204150188610008\"}","utf-8");
        System.out.println(ss);
        System.out.println(s);
        String s1 = URLDecoder.decode(s,"utf-8");
        String s2 = URLDecoder.decode(ss,"iso-8859-1");
        System.out.println(s1);
        System.out.println(s2);
    }
}
