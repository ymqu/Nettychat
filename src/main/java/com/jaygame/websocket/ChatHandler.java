package com.jaygame.websocket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.time.LocalDateTime;

/**
 * Process msg Handler
 * TextWebsocketFrame: used to process text, frame is format of msg, in netty.
 */
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    //manage all clients' channels
    private static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {

        String content = msg.text();

        System.out.printf("content=", content);

        for(Channel channel: clients){
            channel.writeAndFlush(new TextWebSocketFrame("[server time:]" + LocalDateTime.now() + "  [content:]" + content));
        } // the same as:

        //Clients.writeAndFlush(new TextWebSocketFrame("[server time:]" + LocalDateTime.now() + "  [content:]" + content));

    }

    /**
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
       clients.add( ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        clients.remove(ctx.channel());
    }
}
