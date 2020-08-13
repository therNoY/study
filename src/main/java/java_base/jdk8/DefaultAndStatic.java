package java_base.jdk8;

public class DefaultAndStatic {

    public static void main(String[] args) {
        MyInt.staticF();
    }

}


interface MyInt{

    default void defaultF(){
        System.out.println("de");
    }

    static void staticF(){
        System.out.println("st");
    }

}