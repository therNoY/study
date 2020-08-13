package util;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * 配置文件操作的工具类
 */
public class PropertiesUtil {

    /**
     * 根据路径和属性值 返回配置文件的属性
     *
     * @param property
     * @return
     */
    public static String getProperties(String path, String property) {
        InputStream is = PropertiesUtil.class.getClassLoader().getResourceAsStream(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("utf-8")));
        Properties properties = new Properties();
        try {
            properties.load(br);
            return properties.getProperty(property);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据路径返回配置文件
     *
     * @return
     */
    public static Properties getProperties(String path) {
        InputStream is = PropertiesUtil.class.getClassLoader().getResourceAsStream(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("utf-8")));
        Properties properties = new Properties();
        try {
            properties.load(br);
            return properties;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}
