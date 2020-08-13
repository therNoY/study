package design_patterns.active_type.state;

import java.util.HashMap;
import java.util.Map;

/**
 * 维持一个状态类 和 一个状态
 */
public class Context {

    SuccessVote successVote = new SuccessVote();
    RepeatVoteState repeatVoteState = new RepeatVoteState();
    SpiteVoteState spiteVoteState = new SpiteVoteState();

    Map<Voter, Integer> map = new HashMap<>();

    public void vote(Voter voter) {
        Integer i = map.get(voter);
        if (i == null) {
            i = 1;
        } else {
            i ++;
        }
        map.put(voter, i);

        if (i <= 2) {
            successVote.callBack(i);
        }else if (i > 2 && i <= 4) {
            repeatVoteState.callBack(i);
        }else if (i > 4 && i <= 6) {
            spiteVoteState.callBack(i);
        }else {
            System.out.println("拉黑");
        }
    }


    public static void main(String[] args) {
        Context context = new Context();
        Voter voter = new Voter("小明");
        context.vote(voter);
        context.vote(voter);
        context.vote(voter);
        context.vote(voter);
        context.vote(voter);
        context.vote(voter);
        context.vote(voter);
    }
}
