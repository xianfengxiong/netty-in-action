package io.netty.channel;

import io.netty.util.concurrent.EventExecutor;

/**
 * @author xxf
 * @since 2018/8/26
 */
public class DummyChannelHandlerContext extends AbstractChannelHandlerContext {
    public static ChannelHandlerContext DUMMY_INSTANCE = new DummyChannelHandlerContext(
            null,
            null,
            null,
            true,
            true
    );

    public DummyChannelHandlerContext(DefaultChannelPipeline pipeline,
                                    EventExecutor executor,
                                    String name, boolean inbound, boolean outbound) {
        super(pipeline, executor, name, inbound, outbound);
    }

    @Override
    public ChannelHandler handler() {
        return null;
    }
}
