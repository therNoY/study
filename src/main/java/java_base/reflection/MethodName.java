package java_base.reflection;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;

public class MethodName {

    Dog dog = new Dog();

    public static void main(String[] args) throws IntrospectionException {
        MethodName methodName = new MethodName();
        BeanInfo beanInfo = Introspector.getBeanInfo(methodName.getClass(), Object.class);
        System.out.println(beanInfo);
    }

    private class Dog{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
