package cn.wanru.chapter5;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.ByteProcessor;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Random;

/**
 * @author xxf
 * @since 2018/8/25
 */
public class ByteBufExamples {
    private static final Random random = new Random();
    private static final ByteBuf BYTE_BUF_FROM_SOMEWHERE = Unpooled.buffer(1024);


    public static void heapBuffer() {
        ByteBuf heapBuf = BYTE_BUF_FROM_SOMEWHERE;
        if (heapBuf.hasArray()) {
            byte[] array = heapBuf.array();
            int offset = heapBuf.arrayOffset() + heapBuf.readInt();
            int length = heapBuf.readableBytes();
            handleArray(array,offset,length);
        }
    }

    public static void directBuffer() {
        ByteBuf directBuf = BYTE_BUF_FROM_SOMEWHERE;
        if (!directBuf.hasArray()) {
            int length = directBuf.readableBytes();
            byte[] array = new byte[length];
            directBuf.getBytes(directBuf.readerIndex(),array);
            handleArray(array,0,length);
        }
    }

    public static void byteBufferComposite(ByteBuffer header,ByteBuffer body) {
        ByteBuffer[] message = new ByteBuffer[] {header,body};
        ByteBuffer message2 =
            ByteBuffer.allocate(header.remaining() + body.remaining());
        message2.put(header);
        message2.put(body);
        message2.flip();
    }

    public static void byteBufComposite() {
        CompositeByteBuf messageBuf = Unpooled.compositeBuffer();
        ByteBuf headerBuf = BYTE_BUF_FROM_SOMEWHERE;
        ByteBuf bodyBuf = BYTE_BUF_FROM_SOMEWHERE;
        messageBuf.addComponents(headerBuf,bodyBuf);
        messageBuf.removeComponent(0);
        for (ByteBuf buf : messageBuf) {
            System.out.println(buf.toString());
        }
    }

    public static void byteBufCompositeArray() {
        CompositeByteBuf compbuf = Unpooled.compositeBuffer();
        int length = compbuf.readableBytes();
        byte[] array = new byte[length];
        compbuf.getBytes(compbuf.readerIndex(),array);
        handleArray(array,0,length);
    }

    public static void byteBufRelativeAccess() {
        ByteBuf buffer = BYTE_BUF_FROM_SOMEWHERE;
        for (int i = 0; i < buffer.capacity(); i++) {
            byte b = buffer.getByte(i);
            System.out.println((char)b);
        }
    }

    public static void readAll() {
        ByteBuf buffer = BYTE_BUF_FROM_SOMEWHERE;
        while (buffer.isReadable()) {
            System.out.println(buffer.readByte());
        }
    }

    public static void write() {
        ByteBuf buffer = BYTE_BUF_FROM_SOMEWHERE;
        while (buffer.writableBytes() >= 4) {
            buffer.writeInt(random.nextInt());
        }
    }

    public static void byteProcess() {
        ByteBuf buffer = BYTE_BUF_FROM_SOMEWHERE;
        int index = buffer.forEachByte(ByteProcessor.FIND_CR);
    }

    public static void byteBufSlice() {
        Charset utf8 = Charset.forName("UTF-8");
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!",utf8);
        ByteBuf sliced = buf.slice(0, 15);
        System.out.println(sliced.toString());
        buf.setByte(0,(byte)'J');
        assert buf.getByte(0) == sliced.getByte(0);
    }

    public static void byteBufCopy() {
        Charset utf8 = Charset.forName("UTF-8");
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!",utf8);
        ByteBuf copy = buf.copy(0, 15);
        System.out.println(copy.toString());
        buf.setByte(0,(byte)'J');
        assert buf.getByte(0) != copy.getByte(0);
    }

    public static void byteBufSetGet() {
        Charset utf8 = Charset.forName("UTF-8");
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!",utf8);
        System.out.println((char) buf.getByte(0));
        int readIndex = buf.readerIndex();
        int writeIndex = buf.writerIndex();
        buf.setByte(0,(byte)'B');
        System.out.println((char)buf.getByte(0));
        assert readIndex == buf.readerIndex();
        assert writeIndex == buf.writerIndex();
    }

    public static void byteBufWriteRead() {
        Charset utf8 = Charset.forName("UTF-8");
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!",utf8);
        System.out.println((char) buf.getByte(0));
        int readIndex = buf.readerIndex();
        int writeIndex = buf.writerIndex();
        buf.writeByte((byte)'?');
        assert readIndex == buf.readerIndex();
        assert writeIndex != buf.writerIndex();
    }

    private static void handleArray(byte[] array,int offset,int length) {}


}
