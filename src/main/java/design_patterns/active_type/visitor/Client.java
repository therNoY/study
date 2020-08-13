package design_patterns.active_type.visitor;

public class Client {

    public static void main(String[] args) {

        Leader leader = new CEOLeader("小明");
        Leader leader2 = new CTOLeader("小蓝");


        BusinessReport.showStaff(leader);
        BusinessReport.showStaff(leader2);

//        BusinessReport.staffList.forEach(staff -> {
//            visit(leader, staff);
//        });
//        BusinessReport.staffList.forEach(staff -> {
//            visit(leader2, staff);
//        });
    }

    public static void visit(Leader leader, Staff staff) {
        if (staff instanceof Manger) {
            Manger manger = (Manger) staff;
            if (leader instanceof CEOLeader) {
                System.out.println("CEO:" + leader.getName() + "评审 产品经理" + manger.getName() + "; 查看KPI" + manger.getKpi());
            }else {

                System.out.println("CTO:" + leader.getName() + "评审 产品经理" + manger.getName() + "; 查看产品数" + manger.getProtect());
            }
        } else if (staff instanceof Engineer) {
            Engineer engineer = (Engineer) staff;
            if (leader instanceof CEOLeader) {
                System.out.println("CEO:" + leader.getName() + "评审 程序员" + engineer.getName() + "; 查看代码量" + engineer.getKpi());
            }else {
                System.out.println("CTO:" + leader.getName() + "评审 程序员" + engineer.getName() + "; 查看代码量" + engineer.getCode());
            }
        }
    }
}
