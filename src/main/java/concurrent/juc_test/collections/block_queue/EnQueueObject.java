package concurrent.juc_test.collections.block_queue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class EnQueueObject implements Delayed {

    String name;
    Integer age;
    long time;

    public EnQueueObject(String name) {
        this.name = name;
    }

    public EnQueueObject(String name, Integer age) {
        this.name = name;
        this.age = age;
        this.time = System.currentTimeMillis() + (age > 0? TimeUnit.SECONDS.toMillis(age): 0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return time - System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed o) {
        EnQueueObject object = (EnQueueObject) o;
        return age - object.getAge();
    }

    @Override
    public String toString() {
        return "EnQueueObject{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", time=" + time +
                '}';
    }
}
