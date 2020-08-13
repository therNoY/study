package design_patterns.structure_type.proxy.dynamic_proxy;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MainClass {
    public static void main(String[] args) {
//        HttpProxyConnection httpProxyConnection = new HttpProxyConnection();
//        Connection connection = (Connection) httpProxyConnection.bind(HttpConnection.class);
//        connection.get();
        test();
    }

    static void test() {
        Class clazz = MyInterface.class;
        MyInterface myInterface = (MyInterface) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{MyInterface.class}, new MyProxy());
        myInterface.getString();
        myInterface.getString("1");
        myInterface.doSometing();
        myInterface.doSometing(1);
    }
}

interface MyInterface{

    String getString();

    String getString(String s);

    void doSometing();

    void doSometing(Integer i);
}

class MyProxy<T> implements InvocationHandler, Serializable{

    private Class<T> mapperInterface;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }
}
