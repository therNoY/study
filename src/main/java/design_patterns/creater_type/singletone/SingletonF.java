package design_patterns.creater_type.singletone;

/**
 * Holder模式 </p>
 * 核心思想:懒加载+静态变量 </p>
 * 优缺点:线程安全,节省资源,可读性好 </p>
 * 适用范围:适用于多线程
 *
 * 单例模式静态内部类实现方式。我们可以看到单例实例使用final定义，但在编译时无法确定下来，所以在第一次使用StaticInnerSingleton.getInstance()方法时，才会触发静态内部类的加载，也就是延迟加载。这里想指出，如果final定义的变量在编译时无法确定，则在使用时还是会进行类的初始化。
 *
 * 作者：9龙
 * 链接：https://juejin.im/post/5cffa528e51d4556da53d091
 * 来源：掘金
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class SingletonF {

    static class SingletonHolder {
        private static final SingletonF singletonF = new SingletonF();
    }

    public SingletonF() {
    }

    public static SingletonF getSingletonF() {
        return SingletonHolder.singletonF;
    }

}