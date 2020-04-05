package com.mvnikitin.nettychat.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.stereotype.Component;

@Component
public class ClientHandler extends SimpleChannelInboundHandler<String>{

    private Callback onMessageReceivedCallback;

    @Override
    protected void channelRead0(
            ChannelHandlerContext channelHandlerContext,
            String s) throws Exception {
        if(onMessageReceivedCallback != null) {
            onMessageReceivedCallback.callback(s);
        }
    }

    public void setOnMessageReceivedCallback(Callback onMessageReceivedCallback) {
        this.onMessageReceivedCallback = onMessageReceivedCallback;
    }
}