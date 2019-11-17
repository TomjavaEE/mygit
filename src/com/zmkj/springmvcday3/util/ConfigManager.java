package com.zmkj.springmvcday3.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {


    private static Properties properties=null;
    //私有构造器-读取数据库配置文件
    public ConfigManager(){
        System.out.println("读取配置文件被执行");
        String configFile = "database.properties";
        properties = new Properties();
        InputStream is =
                ConfigManager.class.getClassLoader().getResourceAsStream(configFile);
        try {
            properties.load(is);
            is.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public String getValue(String key){
        return properties.getProperty(key);
    }
}
