package cn.wanru.chapter13;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.util.CharsetUtil;

import java.util.List;

/**
 * @author xxf
 * @since 2018/8/31
 */
public class LogEventDecoder extends MessageToMessageDecoder<DatagramPacket> {
    @Override
    protected void decode(ChannelHandlerContext ctx,
        DatagramPacket packet, List<Object> out) throws Exception {
        ByteBuf data = packet.content();
        int idx = data.indexOf(0,data.readerIndex(),LogEvent.SEPARATOR);
        String filename = data.slice(0,idx).toString(CharsetUtil.UTF_8);
        String logMsg = data.slice(idx+1,data.readableBytes())
            .toString(CharsetUtil.UTF_8);
        LogEvent event = new LogEvent(packet.sender(),filename,
            logMsg,System.currentTimeMillis());
        out.add(event);
    }
}

