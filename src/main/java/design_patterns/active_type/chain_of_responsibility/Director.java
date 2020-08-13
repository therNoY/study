package design_patterns.active_type.chain_of_responsibility;

/**
 * 需要先给主管批准
 */
public class Director extends Handle {


    @Override
    protected void handel(LevelRequest levelRequest) {
        if (levelRequest.getDay() < 3) {
            String log = "主管<%s> 审批 <%s> 的请假申请，请假天数： <%d> ，审批结果：<%s> ";
            System.out.println(String.format(log, this.name, levelRequest.getName(), levelRequest.getDay(), "批准"));
        } else {
            System.out.println("主管批准了还要 经理批准");
            nextHandel.handel(levelRequest);
        }
    }
}
