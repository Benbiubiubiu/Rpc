package com.example.protocol;

import org.apache.catalina.*;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;

/**
 * author:Ben
 */
public class HttpServer {
    public void start(String hostname,Integer port){
        Tomcat tomcat = new Tomcat();
        Server server = tomcat.getServer();
        Service service = server.findService("Tomcat");

        Connector connector = new Connector();
        connector.setPort(port);
        Engine engine = new StandardEngine();
        engine.setDefaultHost(hostname);

        StandardHost standardHost = new StandardHost();
        standardHost.setName(hostname);

        String contextPath ="";
        Context context = new StandardContext();
        context.setPath(contextPath);
        context.addLifecycleListener( new Tomcat.FixContextListener());

        standardHost.addChild(context);
        engine.addChild(standardHost);

        service.setContainer(engine);
        service.addConnector(connector);

        tomcat.addServlet(contextPath,"dispatcher",new DispatcherServlet());
        context.addServletMappingDecoded("/*","dispatcher");

        try {
            tomcat.start();
            tomcat.getServer().await();
        } catch (LifecycleException e){
            e.printStackTrace();
        }


    }
}
