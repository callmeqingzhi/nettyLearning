package com.cz.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * HttpObject: �ͻ��˺ͷ���˽���ʱ����������
 */
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    /**
     * ��ȡ�ͻ�������
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        // �ж�msg�ǲ���httpRequest����
        if (msg instanceof HttpRequest) {
            System.out.println("pipeline hashcode:" + ctx.pipeline().hashCode() + " TestHttpServerHandler hashcode:" + this.hashCode());
            System.out.println("�ͻ��˵�ַ��" + ctx.channel().remoteAddress());

            HttpRequest httpRequest = (HttpRequest) msg;
            URI uri = new URI(httpRequest.uri());
            if ("/favicon.ico".equals(uri.getPath())) {
                System.out.println("����ͼ����Դ��������Ӧ");
                return;
            }

            // �ظ���Ϣ�������[httpЭ��]
            ByteBuf byteBuf = Unpooled.copiedBuffer("hello world!", CharsetUtil.UTF_8);
            // ����һ��http��Ӧ
            FullHttpResponse httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuf);
            httpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            httpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH, byteBuf.readableBytes());

            // ����
            ctx.writeAndFlush(httpResponse);
        }
    }
}
