package design_patterns.active_type.state;

public class SpiteVoteState extends AbstractState {

    @Override
    public void callBack(Integer num) {
        System.out.println("恶意刷票给出警告");
    }
}
