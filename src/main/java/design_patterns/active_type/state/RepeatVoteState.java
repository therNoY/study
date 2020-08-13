package design_patterns.active_type.state;

/**
 *  重复投票
 */
public class RepeatVoteState extends AbstractState{

    @Override
    public void callBack(Integer num) {
        System.out.println("投票超过三次 投票无效");

    }
}
