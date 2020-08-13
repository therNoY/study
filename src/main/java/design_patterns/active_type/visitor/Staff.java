package design_patterns.active_type.visitor;

import java.util.Random;

/**
 * 员工类 NODE 被访问的节点
 */
public abstract class Staff {

    protected String name;
    protected int kpi;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKpi() {
        return new Random().nextInt(10);
    }

    public void setKpi(int kpi) {
        this.kpi = kpi;
    }

    public abstract void review(Leader leader);
}
