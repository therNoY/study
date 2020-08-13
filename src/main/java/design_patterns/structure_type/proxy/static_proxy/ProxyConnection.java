package design_patterns.structure_type.proxy.static_proxy;

import design_patterns.structure_type.proxy.Connection;
import design_patterns.structure_type.proxy.HttpConnection;

public abstract class ProxyConnection implements Connection {

    HttpConnection httpConnection;

    public ProxyConnection() {
        this.httpConnection = new HttpConnection();
    }

    @Override
    public void get() {
        handle();
        httpConnection.get();
    }

    @Override
    public void post() {
        handle();
        httpConnection.post();
    }

    abstract void handle();
}
