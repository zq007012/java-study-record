package com.zq.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/4/6 9:33
 */
public class BeanFactory {
    private static final Map<String, Object> IOC = new HashMap<>();

    static {
        try {
            InputStream streamOfBeans = ClassLoader.getSystemClassLoader().getResourceAsStream("beans.xml");
            SAXReader saxReader = new SAXReader();
            Document beans = saxReader.read(streamOfBeans);
            List<Node> nodes = beans.selectNodes("//bean");
            for (Node node : nodes) {
                String key = node.selectSingleNode("@id").getStringValue();
                String className = node.selectSingleNode("@class").getStringValue();
                Object obj = Class.forName(className).getConstructor().newInstance();
                IOC.put(key, obj);
            }
        } catch (DocumentException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    public static Object getBean(String beanName) {
        return IOC.get(beanName);
    }
}
