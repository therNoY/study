package design_patterns.creater_type.singletone;

/**
 * 第四种
 * 饱汉模式_变形2 </p>
 * 核心是:懒加载
 * 优缺点:线程安全,控制了并发性
 */
public class SingletonD {
    private static SingletonD singletonD = null;

    public static SingletonD getInstance() {
        if (singletonD == null) {
            synchronized (SingletonD.class) {
                if (singletonD == null) {
                    singletonD = new SingletonD();
                }
            }
        }
        return singletonD;
    }
}
