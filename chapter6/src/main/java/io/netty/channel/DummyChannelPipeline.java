package io.netty.channel;

/**
 * @author xxf
 * @since 2018/8/25
 */
public class DummyChannelPipeline extends DefaultChannelPipeline {
    public static final ChannelPipeline DUMMY_INSTANCE = new DummyChannelPipeline(null);
    protected DummyChannelPipeline(Channel channel) {
        super(channel);
    }
}
