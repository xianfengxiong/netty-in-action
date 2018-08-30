package cn.wanru.chapter13;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author xxf
 * @since 2018/8/31
 */
public class LogEventHandler extends SimpleChannelInboundHandler<LogEvent> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogEvent event)
        throws Exception {
        StringBuilder builder = new StringBuilder();
        builder.append(event.getReceived())
            .append(" [")
            .append(event.getSource().toString())
            .append("] [")
            .append(event.getLogfile())
            .append("] : ")
            .append(event.getMsg());
        System.out.println(builder.toString());
    }
}
