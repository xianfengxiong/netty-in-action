package cn.wanru.chapter10;

import io.netty.channel.CombinedChannelDuplexHandler;

/**
 * @author xxf
 * @since 2018/8/26
 */
public class CombinedByteCharCodec extends
    CombinedChannelDuplexHandler<ByteToCharDecoder,CharToByteEncoder>{
    public CombinedByteCharCodec() {
        super(new ByteToCharDecoder(),new CharToByteEncoder());
    }
}
