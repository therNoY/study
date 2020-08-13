package design_patterns.active_type.chain_of_responsibility;

/**
 * 责任链模式(Chain of Responsibility Pattern)：避免请求发送者与接收者耦合在一起，让多个对象都有可能接收请求，
 * 将这些对象连接成一条链，并且沿着这条链传递请求，直到有对象处理它为止。职责链模式是一种对象行为型模式。
 */
public class Main {

    public static void main(String[] args) {

        GeneraManager generaManager = new GeneraManager();
        generaManager.setName("张三");
        Manger manger = new Manger();
        manger.next(generaManager);
        manger.setName("李四");
        Director director = new Director();
        director.next(manger);
        director.setName("王五");

        for (int i = 0; i < 10; i++) {
            int day = (int) Math.round(Math.random() * 10);
            System.out.println("准备请假的天数" + day);
            LevelRequest levelRequest = new LevelRequest("小明", "看病", day);
            director.handel(levelRequest);
            System.out.println("+++++++++++++++++++++++++++++++++++");
        }

    }
}
