package design_patterns.active_type.observe;

public interface Reader {

    void getContent(String s);


    void addAuthor(Writer writer);
}
