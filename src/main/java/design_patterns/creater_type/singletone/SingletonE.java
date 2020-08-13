package design_patterns.creater_type.singletone;

/**
 * 第五种
 * 饱汉模式_变形3 </p>
 * 核心思想是:懒加载 </p>
 * 优缺点:线程安全,节省资源 </p>
 * 适用范围:适用于多线程敏捷环境
 *
 */
public class SingletonE {

    /**
     * 这里补充一下关于volatile关键字的理解:
     * 上面用到了一个关键字volatile ,在java语言中volatile可以保证内存可见性,禁止指令重排等.
     * 这里提到可见性,那么什么是可见性呢?所谓可见性是指线程之间的可见性,一个线程修改的状态对另一个
     * 线程是可见的也就是一个线程修改的结果,另一个线程马上就能看到。
     * 看一下它的实现原理：
     * 当对一般普通变量进行读写操作时，每个线程都是从其主内存copy变量到本地CPU缓存中,如果我们
     * 的计算机有多个CPU,每个线程可能在不同的CPU上被处理,也就是每个线程可以copy到不同的CPU
     * cache（高速缓冲存储器）中。volatile修饰的变量不会被缓存在寄存器或其他处理器不可兼得地方，这样保证了每次
     * 读写变量都是从主内存中读，跳过CPU cache这一步当一个线程改变了这个变量的值新值对于其他线程是立即可知的
     */
    private volatile static SingletonE singletonE = null;

    public static SingletonE getInstance() {
        if (singletonE == null) {
            synchronized (SingletonE.class) {
                if (singletonE == null) {
                    singletonE = new SingletonE();
                }
            }
        }
        return singletonE;
    }
}
