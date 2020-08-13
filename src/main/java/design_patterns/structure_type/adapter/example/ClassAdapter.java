package design_patterns.structure_type.adapter.example;


/*类适配器*/
public class ClassAdapter extends Adapee implements Target{
    @Override
    public void sayEnglish() {
        System.out.println("hello");
    }
}
