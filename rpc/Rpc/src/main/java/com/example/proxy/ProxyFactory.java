package com.example.proxy;

import com.example.common.Invocation;
import com.example.protocol.HttpClient;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * author:Ben
 */
public class ProxyFactory {
    public static <T> T getProxy(Class interfaceClass){
        Object proxyInstance = Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Invocation invocation = new Invocation(interfaceClass.getName(),
                        method.getName(),method.getParameterTypes(),args);
                HttpClient httpClient = new HttpClient();
                String res =httpClient.send("localhost",8080,invocation);
                return res;
            }
        });
        return (T) proxyInstance;
    }

}
