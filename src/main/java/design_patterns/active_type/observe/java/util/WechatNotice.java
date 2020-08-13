package design_patterns.active_type.observe.java.util;

public class WechatNotice {

    private String publisher;
    private String articleName;


    public String getPublisher() {
        return publisher;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public WechatNotice(String publisher, String articleName) {
        this.publisher = publisher;
        this.articleName = articleName;
    }
}
