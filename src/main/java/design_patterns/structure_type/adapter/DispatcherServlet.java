package design_patterns.structure_type.adapter;

import java.util.List;

/**
 * Client
 */
public class DispatcherServlet {

    private List<HandlerAdapter> handlerAdapters;

    //初始化handlerAdapters
    private void initHandlerAdapters() {
        //..省略...
    }

    // 遍历所有的 HandlerAdapters，通过 supports 判断找到匹配的适配器
    protected HandlerAdapter getHandlerAdapter(Object handler){
        for (HandlerAdapter ha : this.handlerAdapters) {
            if (ha.supports(handler)) {
                return ha;
            }
        }
        return null;
    }

    // 分发请求，请求需要找到匹配的适配器来处理
    protected void doDispatch(Object handle) throws Exception {

        // 确定当前请求的匹配的适配器.
        HandlerAdapter ha = getHandlerAdapter(handle);

    }

}
