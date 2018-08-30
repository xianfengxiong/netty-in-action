package cn.wanru.chapter8;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.oio.OioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author xxf
 * @since 2018/8/26
 */
public class InvalidBootstrapClient {

    public static void main(String[] args) {
        new InvalidBootstrapClient().bootstrap();
    }

    public void bootstrap() {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
            .channel(OioSocketChannel.class)
            .handler(new SimpleChannelInboundHandler<ByteBuf>() {
                @Override
                protected void channelRead0(ChannelHandlerContext ctx,
                    ByteBuf msg) throws Exception {
                    System.out.println("Received data");
                }
            });
        ChannelFuture future = bootstrap.connect(
            new InetSocketAddress("www.manning.com", 80));
        future.syncUninterruptibly();
    }
}
