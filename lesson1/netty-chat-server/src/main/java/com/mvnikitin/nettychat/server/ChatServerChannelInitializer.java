package com.mvnikitin.nettychat.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
public class ChatServerChannelInitializer
        extends ChannelInitializer<SocketChannel> {
    @Autowired
    private StringDecoder decoder;
    @Autowired
    private StringEncoder encoder;

    @Lookup
    public MainHandler createMainHandler() {
        return null;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        /***************************************
         * НЕ РАБОТАЕТ :(
         **************************************/
        MainHandler handler = createMainHandler();
        socketChannel.pipeline().addLast(decoder, encoder, handler);
    }
}
