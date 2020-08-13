package design_patterns.structure_type.adapter;

public class HttpRequestHandlerAdapter implements HandlerAdapter{

    @Override
    public boolean supports(Object handler) {
        return false;
    }

    public static void main(String[] args) {
    }
}
