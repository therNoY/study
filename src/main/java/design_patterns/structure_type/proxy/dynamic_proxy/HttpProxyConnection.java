package design_patterns.structure_type.proxy.dynamic_proxy;

import design_patterns.structure_type.adapter.DispatcherServlet;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
/*jdk 反射实现 动态代理*/
public class HttpProxyConnection implements InvocationHandler{

    private Object proxyObj;

    Object bind(Object proxyObj) {
        this.proxyObj = proxyObj;
        Class clazz = proxyObj.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    Object bind(Class proxyClass) {
        Class clazz = proxyClass;
        try {
            proxyObj = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        System.out.println("事务开始");
        //执行方法
        result = method.invoke(proxyObj, args);
        System.out.println("事务结束");
        return result;
    }
}
