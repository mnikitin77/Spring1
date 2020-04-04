package com.mvnikitin.nettychat.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class Network {
    private static final String HOST = "localhost";
    private static final int PORT = 8189;
    private SocketChannel channel;

    public Network(Callback messageReceived) {
        Thread t = new Thread(() -> {
            EventLoopGroup workerGroup = new NioEventLoopGroup();
            try {
                Bootstrap b = new Bootstrap();
                b.group(workerGroup)
                        .channel(NioSocketChannel.class)
                        .handler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            public void initChannel(SocketChannel ch) throws Exception {
                                channel = ch;
                                ch.pipeline().addLast(new StringDecoder(), new StringEncoder(), new ClientHandler(messageReceived));
                            }
                        });
                ChannelFuture f = b.connect(HOST, PORT).sync();
                f.channel().closeFuture().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                workerGroup.shutdownGracefully();
            }
        });

        t.start();

    }

    public void sendMessage(String msg) {
        channel.writeAndFlush(msg);
    }

    public void close() {
        channel.close();
    }
}