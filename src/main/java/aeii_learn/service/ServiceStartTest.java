package aeii_learn.service;

import java.io.*;

public class ServiceStartTest {

    private final File configuration_file = new File("server.cfg");

    public static void main(String[] args) throws IOException {
        // test1();
        System.out.println(System.getProperty("user.home") + "/.aeii/");
    }


    static void test1() throws IOException {
        ServiceStartTest serviceStartTest = new ServiceStartTest();
        System.out.println(serviceStartTest.configuration_file.getAbsolutePath());
        /**
         *
         *
         */
        InputStream inputStream = ServiceStartTest.class.getResourceAsStream("/package.json");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String read;
        while ((read = reader.readLine()) != null) {
            System.out.println(read);
        }
        System.out.println("读取结束");
    }
}
