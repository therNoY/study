package design_patterns.active_type.template_method;

public class JavaCourse extends ACourse {
    @Override
    void packageCourse() {
        System.out.println("4. 提供Java课程源代码");
    }

    @Override
    protected boolean needWriteArticle() {
        return true;
    }
}
