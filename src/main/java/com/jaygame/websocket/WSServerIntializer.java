package com.jaygame.websocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class WSServerIntializer extends ChannelInitializer<SocketChannel> {

    protected void initChannel(SocketChannel channel) throws Exception {

        ChannelPipeline pipeline = channel.pipeline();

        /**
         * HttpServerCodec, ChunkedWriteHandler, HttpObjectAggregator provide http basic.
         */
        //websocket based on http
        pipeline.addLast(new HttpServerCodec());
        //big data writer
        pipeline.addLast(new ChunkedWriteHandler());
        //httpMessage aggregator, generate to FullHttpRequest or FullHttpResponse, very popular in netty
        pipeline.addLast(new HttpObjectAggregator(1024 * 64));

        /**
         *  websocket server protocol and websocketPath; WebsocketServerProtocolHandler will process(close, ping,pong).
         *  websocket transfer based on frames. different data type has different frames.
         */

        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));

        //custom handler
        pipeline.addLast(new ChatHandler());

    }
}
