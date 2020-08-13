package design_patterns.active_type.visitor;

import java.util.Random;

public class Manger extends Staff{

    public Manger(String name) {
        super.setName(name);
    }
    @Override
    public void review(Leader leader) {
        leader.visited(this);
    }

    public String getProtect() {
        return new Random().nextInt(10) + "";
    }
}
