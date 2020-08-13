package design_patterns.structure_type.proxy;

public class HttpConnection implements Connection{


    public HttpConnection() {
        System.out.println("HttpConnection构造");
    }

    @Override
    public void get() {
        System.out.println("http get 请求");
    }

    @Override
    public void post() {
        System.out.println("http post 请求");
        get();
    }
}
