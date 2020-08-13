package design_patterns.active_type.visitor;

/**
 * 程序员
 */
public class Engineer extends Staff{

    public Engineer(String name) {
        super.setName(name);
    }

    @Override
    public void review(Leader leader) {
        leader.visited(this);
    }

    public String getCode() {
        return Math.round(Math.random()) * 10 + 90 +"万行";
    }
}
