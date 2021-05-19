package com.zq.task3.xml03;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: DOM4JDemo
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/3/11 9:52
 * @Version: V1.0
 */
public class DOM4JDemo {
    @Test
    public void elementTest() throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document doc = saxReader.read(new File("resources/pirate.xml"));
        //获取根元素并获取根元素下的所有子元素
        Element rootElement = doc.getRootElement();
        List<Element> elements = rootElement.elements();
        System.out.println("根元素的名称是: " + rootElement.getName() +
                ", 有" + elements.size() + "个子元素");

        //解析每一个子元素
        for (Element element :
                elements) {
            System.out.println("===========================================");
            System.out.println(element.getName() +
                    " \tindex=\""+element.attributeValue("index") + "\"");
            List<Element> grandElements = element.elements();
            System.out.println("------------------------");
            for (Element grandElement :
                    grandElements) {
                System.out.println(grandElement.getName() + " \t: \t" +
                        grandElement.getText());
            }
            System.out.println("-------------------------");
            System.out.println("===========================================");

        }
    }
}
