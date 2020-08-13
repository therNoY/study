package design_patterns.creater_type.singletone;

/**
 * 第一种
 * 饿汉模式</p>
 * 核心思想是:类加载时初始化单利 </p>
 * 优缺点:线程安全。浪费资源</p>
 * 适用范围：适用多线程</p>
 */
public class SingletonA {

    private static SingletonA singletonA = new SingletonA();

    public static SingletonA getInstance() {
        return singletonA;
    }
}
