package java_base;

public class StatckNullParm {

    private String name;

    public StatckNullParm(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        StatckNullParm statckNullParm = new StatckNullParm("2");


        Test(statckNullParm);



    }

    static void Test(StatckNullParm statckNullParm){
        statckNullParm = new StatckNullParm("1");
    }

}
