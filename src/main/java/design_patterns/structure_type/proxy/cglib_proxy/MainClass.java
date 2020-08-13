package design_patterns.structure_type.proxy.cglib_proxy;

import design_patterns.structure_type.proxy.Connection;
import design_patterns.structure_type.proxy.HttpConnection;

/**
 * 这里生产的是http 的一个代理对象
 */
public class MainClass {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        CglibProxy cglibProxy = new CglibProxy();
        HttpConnection connection = cglibProxy.getInstance(HttpConnection.class);
        System.out.println(connection.getClass());
        connection.get();
    }
}
