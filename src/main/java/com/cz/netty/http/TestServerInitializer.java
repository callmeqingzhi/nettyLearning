package com.cz.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class TestServerInitializer extends ChannelInitializer<SocketChannel> {

    protected void initChannel(SocketChannel socketChannel) throws Exception {
        // ��ܵ����봦����

        // �õ��ܵ�
        ChannelPipeline pipeline = socketChannel.pipeline();
        // ����һ��netty�ṩ��httpServerCodec (http�ı������) ����һ���Զ����handler
        pipeline.addLast("myCodec", new HttpServerCodec());
        pipeline.addLast("myHandler", new TestHttpServerHandler());

    }
}
