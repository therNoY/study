package java_base.ComparableAndComparator;

import java.util.PriorityQueue;

public class MainTest {
    public static void main(String[] args) {
        Student student = new Student("小明", 10, 90);
        Student student2 = new Student("小红", 11, 80);
        Student student3 = new Student("小蓝", 11, 70);
        Student student4 = new Student("小刚", 9, 80);
        Student student5 = new Student("小智", 10, 88);

//        Student[] students = new Student[5];
//        students[0] = student;students[1] = student2;students[2] = student3;students[3] = student4;students[4] = student5;
        PriorityQueue<Student> studentQueue = new PriorityQueue<>();
        studentQueue.add(student);
        studentQueue.add(student2);
        studentQueue.add(student3);
        studentQueue.add(student4);
        studentQueue.add(student5);

        for (Student s : studentQueue) {
            System.out.println(s.toString());
        }
//        System.out.println("----------------------");
//        Arrays.sort(students, new MyComparator());
//        for (Student s : students) {
//            System.out.println(s.toString());
//        }
    }
}
