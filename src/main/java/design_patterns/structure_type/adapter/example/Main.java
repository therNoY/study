package design_patterns.structure_type.adapter.example;

public class Main {

    public static void main(String[] args) {
        Adapee adapee = new Adapee();
        ClassAdapter classAdapter = new ClassAdapter();
        ObjectAdapter objectAdapter = new ObjectAdapter();


        adapee.sayChinese();
        System.out.println("适配器在不修改类的情况下拥有的方法");
        classAdapter.sayEnglish();
        adapee.sayChinese();

        System.out.println("对象适配器");
        objectAdapter.sayChinese();
        objectAdapter.sayEnglish();
    }
}
