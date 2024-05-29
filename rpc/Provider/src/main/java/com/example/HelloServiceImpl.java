package com.example;

/**
 * author:Ben
 */
public class HelloServiceImpl implements HelloService{
    @Override
    public String sayHello(String name) {
        return "hello: "+name;
    }
}
