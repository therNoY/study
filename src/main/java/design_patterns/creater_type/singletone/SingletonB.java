package design_patterns.creater_type.singletone;

/**
 * 第二种
 * 饱汉模式</p>
 * 核心是:懒加载 </p>
 * 优缺点:线程不安全,节省资源 </p>
 * 适用范围:单线程
 */
public class SingletonB {

    private static SingletonB singletonB = null;

    public static SingletonB getInstance() {
        if (singletonB == null) {
            singletonB = new SingletonB();
        }
        return singletonB;
    }
}
