package design_patterns.structure_type.proxy.cglib_proxy;

import design_patterns.structure_type.proxy.Connection;
import design_patterns.structure_type.proxy.HttpConnection;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
/*cglib 的动态代理 demo*/
public class CglibProxy implements MethodInterceptor {

    Object target;

    HttpConnection getInstance (Class clazz) throws IllegalAccessException, InstantiationException {
        this.target = clazz.newInstance();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);
        return (HttpConnection) enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("事务钱");
        proxy.invokeSuper(obj, args);
        // method.invoke(obj, args);
        System.out.println("事务后");
        return null;
    }
}
