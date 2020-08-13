package design_patterns.active_type.observe;

import java.util.Observer;

public class MainTest {
    public static void main(String[] args) {
        // 新建两个读者
        Reader reader = new RealReader("李四");
        Reader reader2 = new RealReader("王五");
        // 新建一个作者
        Writer w = new TangSna();
        // 作者增加粉丝
        w.addFans(reader);
        // 读者增加关注作者
        reader2.addAuthor(w);
        // 唐三发布新的章节
        w.addContent("钟山风雨");
    }
}
