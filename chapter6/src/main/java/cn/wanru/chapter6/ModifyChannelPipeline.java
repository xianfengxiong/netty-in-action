package cn.wanru.chapter6;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelPipeline;

import static io.netty.channel.DummyChannelPipeline.DUMMY_INSTANCE;

/**
 * @author xxf
 * @since 2018/8/25
 */
public class ModifyChannelPipeline {
    private static final ChannelPipeline CHANNEL_PIPELINE_FROM_SOMEWHERE = DUMMY_INSTANCE;

    public static void modifyPipeline() {
        ChannelPipeline pipeline = CHANNEL_PIPELINE_FROM_SOMEWHERE;
        FirstHandler firstHandler = new FirstHandler();
        pipeline.addLast("handler1",firstHandler);
        pipeline.addFirst("handler2",new SecondHandler());
        pipeline.addLast("handler3",new ThirdHandler());
        pipeline.remove("handler3");
        pipeline.remove(firstHandler);
        pipeline.replace("handler2","handler4",new ForthHandler());
    }


    private static class FirstHandler
        extends ChannelHandlerAdapter {

    }

    private static class SecondHandler
        extends ChannelHandlerAdapter {

    }

    private static class ThirdHandler
        extends ChannelHandlerAdapter {

    }

    private static class ForthHandler
        extends ChannelHandlerAdapter {

    }
}
