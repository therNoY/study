package design_patterns.active_type.chain_of_responsibility;

public class Manger extends Handle{
    @Override
    protected void handel(LevelRequest levelRequest) {

        if (levelRequest.getDay() < 5) {
            String log = "经理<%s> 审批 <%s> 的请假申请，请假天数： <%d> ，审批结果：<%s> ";
            System.out.println(String.format(log, this.name, levelRequest.getName(), levelRequest.getDay(), "批准"));
        } else {
            System.out.println("经理批准了还要 总经理批准");
            nextHandel.handel(levelRequest);
        }
    }
}
