package com.mvnikitin.nettychat.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import javafx.fxml.FXMLLoader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.mvnikitin.nettychat.client")
public class ChatClientConfig {

    @Bean(name="fxmlLoader")
    public FXMLLoader fxmlLoader(@Value("/client.fxml") String fxml) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        return loader;
    }

    @Bean(name="network")
    public Network network(@Value("localhost") String host,
                           @Value("8189") int port) {
        Network network = new Network(host, port);
        return network;
    }

    @Bean(name="workerGroup")
    public EventLoopGroup workerGroup() {
        return new NioEventLoopGroup();
    }

    @Bean(name="bootstrap")
    public Bootstrap bootstrap() {
        return new Bootstrap();
    }

    @Bean(name="encoder")
    public StringEncoder encoder() {
        return new StringEncoder();
    }

    @Bean(name="decoder")
    public StringDecoder decoder() {
        return new StringDecoder();
    }

    @Bean(name="handler")
    public ClientHandler handler() {
        return new ClientHandler();
    }
}
