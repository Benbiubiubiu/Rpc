package com.example;

import com.example.protocol.HttpServer;
import com.example.register.LocalRegister;

/**
 * author:Ben
 */
public class Provider {
    public static void main(String[] args) {
        LocalRegister.regist(HelloService.class.getName(),"1.0",HelloServiceImpl.class);
        HttpServer httpServer = new HttpServer();
        httpServer.start("localhost",8080);

    }
}
