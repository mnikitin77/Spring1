package com.mvnikitin.nettychat.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component("chatServer")
public class ChatServerImpl implements ChatServer {
    private int port;

    @Autowired
    ApplicationContext context;

    @Autowired
    private EventLoopGroup bossGroup;
    @Autowired
    private EventLoopGroup workerGroup;
    @Autowired
    private ServerBootstrap bootstrap;
    @Autowired
    private StringDecoder decoder;
    @Autowired
    private StringEncoder encoder;
//    @Autowired
//    private ChatServerChannelInitializer channelInitializer;

    public ChatServerImpl(int port) {
        this.port = port;
    }

    @Override
    public void setBossGroup(EventLoopGroup bossGroup) {
        this.bossGroup = bossGroup;
    }

    @Override
    public void setWorkerGroup(EventLoopGroup workerGroup) {
        this.workerGroup = workerGroup;
    }

    @Override
    public void setBootstrap(ServerBootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    @Override
    public void setDecoder(StringDecoder decoder) {
        this.decoder = decoder;
    }

    @Override
    public void setEncoder(StringEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public void start() {
        try {
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
//                    .childHandler(channelInitializer);
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel)
                                throws Exception {
                            //socketChannel.pipeline().addLast(decoder, encoder, new MainHandler());
                            MainHandler handler = context.getBean(MainHandler.class);
                            socketChannel.pipeline().addLast(decoder, encoder, handler);
                        }
                    });
            ChannelFuture future = bootstrap.bind(port).sync();
            System.out.println("Сервер запущен!");
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}