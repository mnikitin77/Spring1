package com.mvnikitin.nettychat.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.mvnikitin.nettychat.server")
public class ChatServerConfig {
    @Bean(name="chatServer")
    public ChatServer chatServer(
            @Value("8189") int port) {
        ChatServer server = new ChatServerImpl(port);
        return server;
    }

    @Bean(name="bossGroup")
    public EventLoopGroup bossGroup(@Value("1") int threads) {
        return new NioEventLoopGroup(threads);
    }

    @Bean(name="workerGroup")
    public EventLoopGroup workerGroup() {
        return new NioEventLoopGroup();
    }

    @Bean(name="bootstrap")
    public ServerBootstrap bootstrap() {
        return new ServerBootstrap();
    }

    @Bean(name="encoder")
    public StringEncoder encoder() {
        return new StringEncoder();
    }

    @Bean(name="decoder")
    public StringDecoder decoder() {
        return new StringDecoder();
    }
}
