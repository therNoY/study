package design_patterns.structure_type.proxy.static_proxy;

public class MainClass {
    public static void main(String[] args) {

        ProxyConnection proxyConnection = new ProxyConnection() {
            @Override
            void handle() {
                System.out.println("处理实现");
            }
        };
        proxyConnection.get();
    }
}
