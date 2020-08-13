package java_base.ComparableAndComparator;

import java.util.Comparator;

public class MyComparator implements Comparator<Student> {

    @Override
    public int compare(Student student, Student otherStudent) {
        return student.getScore().compareTo(otherStudent.getScore());
    }
}
