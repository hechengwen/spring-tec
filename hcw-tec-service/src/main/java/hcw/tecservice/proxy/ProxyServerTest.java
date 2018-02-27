package hcw.tecservice.proxy;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/2/5 14:56
 * Description:
 * Others:
 */
public class ProxyServerTest {

    public static void main(String[] args) {
        Server server = (Server) new ProxyServer().getInstance(new ServerImpl());
        server.printa("b");
    }
}
