package jvm_test.ClassLoader;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

public class ClassloaderPropTest {
    public static void main(String[] args) throws IOException {
        //获取系统类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println("系统类加载器：" + systemClassLoader.getResource(""));
        /*
        获取系统类加载器的加载路径——通常由CLASSPATH环境变量指定，如果操作系统没有指定
        CLASSPATH环境变量，则默认以当前路径作为系统类加载器的加载路径
         */
        Enumeration<URL> eml = systemClassLoader.getResources("");
        while (eml.hasMoreElements()) {
            System.out.println(eml.nextElement());
        }
        //获取系统类加载器的父类加载器，得到扩展类加载器
        ClassLoader extensionLoader = systemClassLoader.getParent();
        System.out.println("系统类的父加载器是扩展类加载器：" + extensionLoader);
        System.out.println("扩展类加载器的加载路径：" + System.getProperty("java.ext.dirs"));
        System.out.println("扩展类加载器的parant：" + extensionLoader.getParent());
    }
}
//输出结果
//系统类加载器：sun.misc.Launcher$AppClassLoader@18b4aac2
//file:/C:/ProjectTest/FengKuang/out/production/FengKuang/
//系统类的父加载器是扩展类加载器：sun.misc.Launcher$ExtClassLoader@1540e19d
//扩展类加载器的加载路径：C:SorftwareInstalljavajdkjrelibext;C:WINDOWSSunJavalibext
//扩展类加载器的parant：null