package com.example;

import com.example.common.Invocation;
import com.example.protocol.HttpClient;
import com.example.proxy.ProxyFactory;

import java.lang.reflect.Proxy;

/**
 * author:Ben
 */
public class Consumer {
    public static void main(String[] args) {
        HelloService helloService = ProxyFactory.getProxy(HelloService.class);
        String ans = helloService.sayHello("ben");
        System.out.println(ans);

    }


}
