package design_patterns.active_type.visitor;

public class CEOLeader extends Leader{


    public CEOLeader(String name) {
        super(name);
    }

    @Override
    public void visited(Manger manger) {
        System.out.println("CEO:" + name + "评审 产品经理" + manger.getName() + "; 查看KPI" + manger.getKpi());
    }

    @Override
    public void visited(Engineer engineer) {
        System.out.println("CEO:" + name + "评审 程序员" + engineer.getName() + "; 查看KPI" + engineer.getKpi());
    }
}
