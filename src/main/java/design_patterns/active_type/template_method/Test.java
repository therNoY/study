package design_patterns.active_type.template_method;

public class Test {
    public static void main(String[] args) {
        System.out.println("Java课程start---");
        ACourse javaCourse = new JavaCourse();
        javaCourse.makeCourse();
        System.out.println("Java课程end---\n");


        System.out.println("前端课程start---");
        ACourse feCourse = new FECourse(false);
        feCourse.makeCourse();
        System.out.println("前端课程end---");
    }
}

