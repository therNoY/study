package jvm_test.ClassLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

public class MyClassloader extends ClassLoader {

    /**
     * 读取文件内容
     *
     * @param fileName 文件名
     * @return
     */
    private byte[] getBytes(String fileName) throws IOException {
        File file = new File(fileName);
        long len = file.length();
        byte[] raw = new byte[(int) len];
        try (FileInputStream fin = new FileInputStream(file)) {
            //一次性读取Class文件的全部二进制数据
            int read = fin.read(raw);
            if (read != len) {
                throw new IOException("无法读取全部文件");
            }
            return raw;
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class clazz = null;
        //将包路径的(.)替换为斜线(/)
        String fileStub = name.replace(".", "/");
        String classFileName = fileStub + ".class";
        File classFile = new File("C:\\Users\\Administrator\\Desktop\\Hello.class");

        //如果Class文件存在，系统负责将该文件转换为Class对象
        if (classFile.exists()) {
            try {
                //将Class文件的二进制数据读入数组
                byte[] raw = getBytes("C:\\Users\\Administrator\\Desktop\\Hello.class");
                //调用ClassLoader的defineClass方法将二进制数据转换为Class对象
                clazz = defineClass(name, raw, 0, raw.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //如果clazz为null,表明加载失败，抛出异常
        if (null == clazz) {
            throw new ClassNotFoundException(name);
        }
        return clazz;
    }

    public static void main(String[] args) throws Exception {
        String classPath = "jvm_test.ClassLoader.Hello";
        MyClassloader myClassloader = new MyClassloader();
        Class<?> aClass = myClassloader.loadClass(classPath);
        Method main = aClass.getMethod("hello", String.class);
        System.out.println(main);
        main.invoke(aClass.newInstance(), "Hello World");
    }
}