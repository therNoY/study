package nio.buffer;

import sun.nio.ch.IOUtil;

import java.nio.ByteBuffer;


/**
 * ByteBuffer 包括堆上的和堆外内存的 ByteBuffer.allocate 请求分配堆内
 * ByteBuffer.allocateDirect 请求分配堆外其实是使用byte 数组来用的
 *
 * byte 是 整形：byte short int long 8 16 32 64
 * 1 byte = 8 字节
 * 1 kb = 1024 byte
 * 1 mb = 1024 * 1024 byte
 */
public class MainTest {

    public static void main(String[] args) {

//        baseMethods();
        sysArrayCopy();
//        testCompact();
    }


    /**
     * 这个方法是将数组后面的放到前面去 可能会用到, 然后position 从后面开始
     */
    private static void testCompact() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);

        byteBuffer.put((byte) 1);
        byteBuffer.put((byte) 2);
        byteBuffer.put((byte) 3);
        byteBuffer.put((byte) 4);
        byteBuffer.put((byte) 5);
        byteBuffer.put((byte) 6);
        byteBuffer.put((byte) 7);
        byteBuffer.put((byte) 8);
        byteBuffer.position(1);

        byteBuffer.compact();

        byteBuffer.put((byte) 2);
        byteBuffer.put((byte) 3);

    }

    private static void baseMethods() {




        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        /* 剩余的大小  limit - position 读写均适用*/
        byteBuffer.remaining();

        /* put 操作posion 加1 在position位置数组上赋值 */
        for (int i = 0; i < 20; i++) {
            byteBuffer.put((byte) i);
        }

        String s = "12345";

        byte[] bytes = s.getBytes();
        byteBuffer.put(bytes);

        /* 直接返回整个数组 */
        byteBuffer.array();

        /* 读模式改成写模式 */
        byteBuffer.flip();

        /* 并没初始化数据只是修改位置 */
        byteBuffer.clear();

        byteBuffer.compact();
        /* 写变成读 */
        byteBuffer.flip();

        byteBuffer.hasRemaining();

        byteBuffer.get();

        /* 新建一个一样的 */
        byteBuffer.duplicate();

        byteBuffer.rewind();
    }

    public static void sysArrayCopy(){

        String[] src= {"1", "2", "3", "4", "5", "6", "7"};
        String[] target = new String[10];

        /**
         * 复制目标数组从srcPos开始 到 目标路径从destPos开始 长度length
         */
        System.arraycopy(src, 2, target, 3, 3);

    }
}
