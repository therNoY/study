package design_patterns.creater_type.prototype;

/**
 * 原型模式测试类
 */
public class PrototypeMain {

    public static void main(String[] args) throws CloneNotSupportedException {
        Dog dog = new Dog("大黄", 1, 3);
        Person person = new Person("小明", 1, "杭州", dog);
        Person person2 = person.clone();

        System.out.println(person2.equals(person));
        System.out.println(person2 == person);
        System.out.println(person2.getPet() == person.getPet());
        System.out.println(person2.getPet().equals(person.getPet()));
    }
}
