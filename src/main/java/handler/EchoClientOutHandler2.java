package handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.util.CharsetUtil;

/**
 * Created by mengfw on 2017/7/20.
 */
public class EchoClientOutHandler2   extends ChannelOutboundHandlerAdapter {
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println("Client write2 :" + ((ByteBuf)msg).toString(CharsetUtil.UTF_8));
        ctx.writeAndFlush(msg);
    }
}
