package java_base.enumTest;

import java.util.ArrayList;

public class MainClass {
    public static void main(String[] args) {
        testEnum1();
//        OperationTypeEnum operationTypeEnum = null;
//        testEnum3(operationTypeEnum);

//        MyEnum.instance.say();
    }

    public static void testEnum1 (){
        System.out.println(Enum1.BLUE.getKey());
    }
    public static void testEnum2 () {
        Enum2 enum2 = Enum2.valueOf("A");
        switch (enum2) {
            case A:
                break;
        }
        System.out.println(Enum2.A);
    }

    public static void testEnum3 (OperationTypeEnum enums){
        for (Enum e : enums.values()) {
            System.out.println(e.toString());
        }
    }
}
