package com.example;

import com.example.common.URL;
import com.example.protocol.HttpServer;
import com.example.register.LocalRegister;
import com.example.register.MapRemoteRegister;

/**
 * author:Ben
 */
public class Provider {
    public static void main(String[] args) {
        LocalRegister.regist(HelloService.class.getName(),"1.0",HelloServiceImpl.class);

        URL url = new URL("localhost",8080);
        MapRemoteRegister.regist(HelloService.class.getName(),url);

        HttpServer httpServer = new HttpServer();
        httpServer.start(url.getHostname(),url.getPort());

    }
}
