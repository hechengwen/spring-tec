package hcw.tecservice.learn.ssl;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.util.NodeList;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.security.SecureRandom;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/3/13 18:23
 * Description:
 * Others:
 */
public class HttpsRequest {

    /**
     *
     * @param requestUrl
     * @param requestMethod
     * @param data
     * @return
     */
    public static String httpsRequest(String requestUrl,String requestMethod,String data){
        StringBuffer buffer=new StringBuffer();
        try {
            SSLContext sslContext = SSLContext.getInstance("SSL");
            TrustManager[] tm = {new MyX509TrustManager()};

            sslContext.init(null,tm,new SecureRandom());
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection conn=(HttpsURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod(requestMethod);
            //设置当前实例使用的SSLSoctetFactory
            conn.setSSLSocketFactory(ssf);
            conn.connect();
            if(null!=data){
                OutputStream os=conn.getOutputStream();
                os.write(data.getBytes("utf-8"));
                os.close();
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                buffer.append(line);
            }

        } catch(Exception e){
            e.printStackTrace();
        }
        return buffer.toString();
    }

    /**
     * 利用htmlparser提取网页纯文本
     * @param inputHtml
     * @return
     * @throws Exception
     */
    public static String extractText(String inputHtml) throws Exception {
        StringBuffer text = new StringBuffer();
        Parser parser = Parser.createParser(new String(inputHtml.getBytes(),"utf-8"), "utf-8");
        // 遍历所有的节点
        NodeList nodes = parser.extractAllNodesThatMatch(new NodeFilter() {
            public boolean accept(Node node) {
                return true;
            }
        });

        System.out.println(nodes.size()); //打印节点的数量
        for (int i=0;i<nodes.size();i++){
            Node nodet = nodes.elementAt(i);
            //System.out.println(nodet.getText());
            text.append(new String(nodet.toPlainTextString().getBytes("utf-8"))+"/r/n");
        }
        return text.toString();
    }
}
