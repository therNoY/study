package design_patterns.active_type.visitor;


/**
 * 领导类 访问节点NODe 的访问者
 */
public abstract class Leader {

    protected String name;

    public Leader(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void visited(Manger manger);

    public abstract void visited(Engineer engineer);
}
