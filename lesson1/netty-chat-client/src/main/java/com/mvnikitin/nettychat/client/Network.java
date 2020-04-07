package com.mvnikitin.nettychat.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Network {
    private String host;
    private int port;
    private SocketChannel channel;

    @Autowired
    private EventLoopGroup workerGroup;
    @Autowired
    private Bootstrap bootstrap;
    @Autowired
    private StringDecoder decoder;
    @Autowired
    private StringEncoder encoder;
    @Autowired
    private ClientHandler handler;

    public Network(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void init(Callback messageReceived) {
        handler.setOnMessageReceivedCallback(messageReceived);
        new Thread(() -> {
            try {
                bootstrap.group(workerGroup)
                        .channel(NioSocketChannel.class)
                        .handler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            public void initChannel(SocketChannel ch) throws Exception {
                                channel = ch;
                                ch.pipeline().addLast(decoder, encoder, handler);
                            }
                        });
                ChannelFuture f = bootstrap.connect(host, port).sync();
                f.channel().closeFuture().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                workerGroup.shutdownGracefully();
            }
        }).start();
    }

    public void sendMessage(String msg) {
        channel.writeAndFlush(msg);
    }

    public void close() {
        channel.close();
    }
}