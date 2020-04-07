package com.mvnikitin.nettychat.server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServerApp {
    public static void main(String[] args) {
//        ApplicationContext context =
//                new ClassPathXmlApplicationContext("chatserver-config.xml");
        ApplicationContext context =
                new AnnotationConfigApplicationContext(ChatServerConfig.class);
        ChatServer server = context.getBean("chatServer", ChatServer.class);
        server.start();
    }
}
