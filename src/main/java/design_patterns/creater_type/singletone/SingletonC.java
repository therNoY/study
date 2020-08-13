package design_patterns.creater_type.singletone;

/**
 * 第三种
 * 饱汉模式_变形1</p>
 * 核心思想:懒加载 </p>
 * 优缺点:线程安全,并发性差 </p>
 */
public class SingletonC {

    private static SingletonC singletonC = null;

    public synchronized static SingletonC getInstance() {
        if (singletonC == null) {
            singletonC = new SingletonC();
        }
        return singletonC;
    }
}
