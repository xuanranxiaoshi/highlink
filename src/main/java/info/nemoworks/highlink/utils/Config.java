package info.nemoworks.highlink.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @description:
 * @author：jimi
 * @date: 2024/4/2
 * @Copyright：
 */
public class Config {
    private static Properties properties;

    static {

        // 读取YAML文件
        properties = new Properties();

        try (InputStream resourceAsStream = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getProperty(String key){
        return (String) properties.get(key);
    }

}
