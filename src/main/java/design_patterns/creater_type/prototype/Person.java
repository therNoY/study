package design_patterns.creater_type.prototype;

import java.util.Objects;

public class Person extends Animal {

    private String name;

    private String add;

    private Dog pet;

    public Person() {
    }

    public Person(String name, Integer sex, String add, Dog pet) {
        this.name = name;
        super.setSex(sex);
        this.add = add;
        this.pet = pet;
    }


    /*
    * 深度克隆需要自己手动实现
    * */
    @Override
    protected Person clone() throws CloneNotSupportedException {
        Person p = (Person) super.clone();
        p.pet = (Dog) p.pet.clone();
        return p;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public Dog getPet() {
        return pet;
    }

    public void setPet(Dog pet) {
        this.pet = pet;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Person person = (Person) object;
        return Objects.equals(name, person.name) &&
                Objects.equals(add, person.add) &&
                Objects.equals(pet, person.pet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, add, pet);
    }
}
