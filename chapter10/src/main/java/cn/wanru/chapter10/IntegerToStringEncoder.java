package cn.wanru.chapter10;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * @author xxf
 * @since 2018/8/26
 */
public class IntegerToStringEncoder extends MessageToMessageEncoder<Integer> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Integer msg,
        List<Object> out) throws Exception {
        out.add(String.valueOf(msg));
    }
}
