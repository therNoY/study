package java_base.ComparableAndComparator;

public class Student implements Comparable<Student>{

    public Student(String name, Integer age, Integer score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    private String name;
    private Integer age;
    private Integer score;

    @Override
    public int compareTo(Student otherStudent) {
       return this.getAge().compareTo(otherStudent.getAge());
    }

    @Override
    public String toString() {
        return this.getName()+" "+this.getAge()+" "+this.getScore();
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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
