package design_patterns.active_type.observe.java.util;

import java.util.Observable;
import java.util.Observer;

public class WeChatClient implements Observer {
    private String username;

    public WeChatClient(String username) {
        this.username = username;
    }

    @Override
    public void update(Observable o, Object arg) {
        //WeChatAccounts weChatAccounts = (WeChatAccounts) o;
        WechatNotice notice = (WechatNotice) arg;
        System.out.println(String.format("用户<%s> 接收到 <%s>微信公众号 的推送，文章标题为 <%s>", username, notice.getPublisher(), notice.getArticleName()));
    }
}

class WeChatAccounts extends Observable {
    private String name;

    public WeChatAccounts(String name) {
        this.name = name;
    }

    public void publishArticles(String articleName, String content) {
        System.out.println(String.format("\n<%s>微信公众号 发布了一篇推送，文章名称为 <%s>，内容为 <%s> ", this.name, articleName, content));
        setChanged();
        notifyObservers(new WechatNotice(this.name, articleName));
    }
}
