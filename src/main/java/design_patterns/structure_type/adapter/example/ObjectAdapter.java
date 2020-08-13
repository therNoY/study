package design_patterns.structure_type.adapter.example;

/*对象适配器*/
public class ObjectAdapter implements Target{

    private Adapee adapee = new Adapee();

    @Override
    public void sayEnglish() {
        System.out.println("hello");
    }

    public void sayChinese(){
        adapee.sayChinese();
    }
}
