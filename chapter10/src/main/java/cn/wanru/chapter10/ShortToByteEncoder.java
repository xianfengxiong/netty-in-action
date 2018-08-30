package cn.wanru.chapter10;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author xxf
 * @since 2018/8/26
 */
public class ShortToByteEncoder extends MessageToByteEncoder<Short> {
    @Override
    protected void encode(ChannelHandlerContext ctx,
        Short msg, ByteBuf out) throws Exception {
        out.writeShort(msg);
    }
}
