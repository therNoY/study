package design_patterns.active_type.visitor;

public class CTOLeader extends Leader{


    public CTOLeader(String name) {
        super(name);
    }

    @Override
    public void visited(Manger manger) {
        System.out.println("CTO:" + name + "评审 产品经理" + manger.getName() + "; 查看产品数" + manger.getProtect());
    }

    @Override
    public void visited(Engineer engineer) {
        System.out.println("CTO:" + name + "评审 程序员" + engineer.getName() + "; 查看代码量" + engineer.getCode());
    }
}
