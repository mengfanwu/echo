package handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.Scanner;

/**
 * Created by mengfw on 2017/7/20.
 */
public class EchoClientHandler2 extends ChannelInboundHandlerAdapter {
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Client received2 :" + ((ByteBuf)msg).toString(CharsetUtil.UTF_8));
        ctx.fireChannelRead(msg);
    }
}
