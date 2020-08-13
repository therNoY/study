package java_base.Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) //Retention注解决定MyAnnotation注解的生命周期
@Target( { ElementType.METHOD, ElementType.TYPE }) //Target注解决定MyAnnotation注解可以加在哪些成分上，如加在类身上，或者属性身上，或者方法身上等成分
// @Deprecated
/*
* @Retention(RetentionPolicy.SOURCE)
* 这个注解的意思是让MyAnnotation注解只在java源文件中存在，编译成.class文件后注解就不存在了
* @Retention(RetentionPolicy.CLASS)
* 这个注解的意思是让MyAnnotation注解在java源文件(.java文件)中存在，编译成.class文件后注解也还存在，
* 被MyAnnotation注解类标识的类被类加载器加载到内存中后MyAnnotation注解就不存在了
*/
public @interface MyAnnotation {

    String color() default "blue";

    String value();
}
