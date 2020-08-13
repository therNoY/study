package design_patterns.active_type.visitor;


import java.util.ArrayList;
import java.util.List;

/**
 * 业务报表类 存放着可被访问的节点的集合 Object
 */
public class BusinessReport {

    public static List<Staff> staffList = new ArrayList<>();

    static {
        staffList.add(new Engineer("员A"));
        staffList.add(new Engineer("员B"));
        staffList.add(new Engineer("员C"));
        staffList.add(new Manger("经理1"));
        staffList.add(new Manger("经理2"));
    }

    public static void showStaff(Leader leader) {
        staffList.forEach(staff -> {
            staff.review(leader);
            // 这里我首先想到是这样的访问方式 凡事leader 没有抽象类的访问方法
//            leader.visited(staff);
        });
    }
}
