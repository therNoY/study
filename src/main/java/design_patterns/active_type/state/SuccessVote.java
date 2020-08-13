package design_patterns.active_type.state;

/**
 * 成功投票
 */
public class SuccessVote extends AbstractState{

    @Override
    public void callBack(Integer num) {
        System.out.println("恭喜投票成功");
    }
}
