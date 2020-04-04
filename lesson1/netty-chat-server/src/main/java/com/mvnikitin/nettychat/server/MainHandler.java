package com.mvnikitin.nettychat.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.ArrayList;
import java.util.List;

public class MainHandler extends SimpleChannelInboundHandler<String> {
    private static final List<Channel> channels = new ArrayList<>();
    private static int newClientIndex = 1;
    private String clientName;

    @Override
    public void channelActive(final ChannelHandlerContext ctx) {
        System.out.println("Клиент подключился - " + ctx);
        channels.add(ctx.channel());
        clientName = "Клиент #" + newClientIndex;
        newClientIndex++;
        broadcastMessage("SERVER", "Подключился клиент " + clientName);
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("Получено сообщение - " + msg);
        if (msg.startsWith("/")) {
            if (msg.startsWith("/changename ")) {
                String newName = msg.split("\\s", 2)[1];
                broadcastMessage("SERVER", "Клиент " +
                        clientName + " сменил имя на + " + newName);
                clientName = newName;
            }
            return;
        }
        broadcastMessage(clientName, msg);
    }

    public void broadcastMessage(String clientName, String message) {
        String out = String.format("[%s]: %s\n", clientName, message);
        for (Channel c : channels) {
            c.writeAndFlush(out);
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Клиент отвалился - " + ctx);
        channels.remove(ctx.channel());
        broadcastMessage("SERVER", "Клиент " + clientName + " покинул чат");
        super.channelInactive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}