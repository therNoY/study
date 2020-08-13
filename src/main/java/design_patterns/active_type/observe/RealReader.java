package design_patterns.active_type.observe;

public class RealReader implements Reader{

    private String name;

    public RealReader(String name) {
        this.name = name;
    }

    @Override
    public void getContent(String s) {
        System.out.println(this.name + "收到了" + s);
    }

    public void addAuthor(Writer writer){
        writer.addFans(this);
    }
}
