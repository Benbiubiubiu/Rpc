package com.example.proxy;

import com.example.common.Invocation;
import com.example.common.URL;
import com.example.loadbalance.LoadBalance;
import com.example.protocol.HttpClient;
import com.example.register.MapRemoteRegister;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

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

                //服务发现
                List<URL> urls = MapRemoteRegister.get(interfaceClass.getName());
                //负载均衡
                URL url = LoadBalance.random(urls);
                String res = null;
                try {
                    res =httpClient.send(url.getHostname(),url.getPort(),invocation);
                }catch (Exception e){
                    System.out.println("error");
//                  容错逻辑
                }

                return res;
            }
        });
        return (T) proxyInstance;
    }

}
