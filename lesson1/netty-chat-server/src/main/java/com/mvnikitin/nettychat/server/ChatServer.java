package com.mvnikitin.nettychat.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public interface ChatServer {
    void start();
    void setBossGroup(EventLoopGroup bossGroup);
    void setWorkerGroup(EventLoopGroup workerGroup);
    void setBootstrap(ServerBootstrap bootstrap);
    void setEncoder(StringEncoder encoder);
    void setDecoder(StringDecoder decoder);
}
