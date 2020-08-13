package design_patterns.creater_type.prototype;

public abstract class Animal implements Cloneable{

    protected Integer sex;

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }
}
