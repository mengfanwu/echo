package client;

import handler.EchoClientHandler;
import handler.EchoClientOutHandler;
import handler.EchoClientOutHandler2;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * Created by mengfw on 2017/7/18.
 */
public class EchoClient {
    private final String host;
    private final int port;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try{
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host,port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new EchoClientOutHandler2())
                                    .addLast(new EchoClientHandler()).addLast(new EchoClientOutHandler());
                        }
                    });
            ChannelFuture future = b.connect().sync();
            future.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        if(args.length != 2 ){
            System.err.println("Usage:" +EchoClient.class.getSimpleName() + "<host> <port>");
            return;
        }
        String host = args[0];
        int port = Integer.parseInt(args[1]);
        new EchoClient(host,port).start();

    }
}
