package jvm_test.ClassLoader;


/**
 * 要解析一个未被解析过的字段符号引用，首先将会对字段表内class_index[2]项中索引的 CONSTANT_Class_info符号引用进行解析，
 * 也就是字段所属的类或接口的符号引用。如果在 解析这个类或接口符号引用的过程中出现了任何异常
 * ，都会导致字段符号引用解析的失败。 如果解析成功完成，那将这个字段所属的类或接口用C表示，
 * 虚拟机规范要求按照如下步骤 对C进行后续字段的搜索。
 *
 * 1）如果C本身就包含了简单名称和字段描述符都与目标相匹配的字段，则返回这个字段 的直接引用，查找结束。
 *
 * 2）否则，如果在C中实现了接口，将会按照继承关系从下往上递归搜索各个接口和它的 父接口，如果接口中包含了简单
 * 名称和字段描述符都与目标相匹配的字段，则返回这个字段 的直接引用，查找结束。
 *
 * 3）否则，如果C不是java.lang.Object的话，将会按照继承关系从下往上递归搜索其父 类，如果在父类中包
 * 含了简单名称和字段描述符都与目标相匹配的字段，则返回这个字段的 直接引用，查找结束。
 *
 * 4）否则，查找失败，抛出java.lang.NoSuchFieldError异常。
 */
public class FieldResolution {
    public static void main(String[] args) {
        System.out.println(Sub.A);
    }
}


interface Interface0 {
    int A = 0;
}

interface Interface1 extends Interface0 {
    int A = 1;
}

interface Interface2 {
    int A = 2;
}

class Parent implements Interface1 {
    public static int A = 3;
}

class Sub extends Parent implements Interface2 {
    public static int A = 4;
}