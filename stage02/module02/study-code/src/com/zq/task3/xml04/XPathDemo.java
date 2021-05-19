package com.zq.task3.xml04;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @ClassName: XPathDemo
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/3/11 21:30
 * @Version: V1.0
 */
public class XPathDemo {
    @Test
    public void xpathTest1() throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read("resources/book.xml");

        String xpath1 = "/bookstore/book";
        Node singleNode = document.selectSingleNode(xpath1);
        System.out.println(singleNode.getName());
        System.out.println("----------------------------------");
        List<Node> nodes = document.selectNodes(xpath1);
        System.out.println("共有" + nodes.size() + "个节点");
        for (Node node :
                nodes) {
            System.out.println(node.getName());
        }

    }

    @Test
    public void xpathTest2() throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read("resources/book.xml");

        String xpath1 = "/bookstore/book[2]";
        Node singleNode = document.selectSingleNode(xpath1);
        System.out.println(singleNode.getName());
        System.out.println("----------------------------------");
        List<Node> nodes = document.selectNodes(xpath1);
        System.out.println("共有" + nodes.size() + "个节点");
        for (Node node :
                nodes) {
            System.out.println(node.getName());
        }
    }

    @Test
    public void xpathTest3() throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read("resources/book.xml");

        String xpath = "/bookstore/book[@index='book3']";
        Node singleNode = document.selectSingleNode(xpath);
        System.out.println(singleNode.getName());
        System.out.println("----------------------------------");
        List<Node> nodes = document.selectNodes(xpath);
        System.out.println("共有" + nodes.size() + "个节点");
        for (Node node :
                nodes) {
            System.out.println(node.getName());
        }
    }

    @Test
    public void xpathTest4() throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read("resources/book.xml");

        String xpath = "/bookstore/book/attribute::index";
        Node singleNode = document.selectSingleNode(xpath);
        System.out.println(singleNode.getName());
        System.out.println("----------------------------------");
        List<Node> nodes = document.selectNodes(xpath);
        System.out.println("共有" + nodes.size() + "个节点");
        for (Node node :
                nodes) {
            System.out.println(node.getName());
        }
    }
    @Test
    public void xpathTest5() throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read("resources/book.xml");

        String xpath = "/bookstore/book/name";
        Node singleNode = document.selectSingleNode(xpath);
        System.out.println(singleNode.getText());
        System.out.println(singleNode.getName());
        System.out.println(singleNode.getStringValue());
        System.out.println(singleNode.getPath());
        System.out.println(singleNode.getNodeTypeName());
    }

    @Test
    public void xpathTest6() throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read("resources/book.xml");

        String xpath = "/bookstore/book/@index";
        Node singleNode = document.selectSingleNode(xpath);
        System.out.println(singleNode.getText());
        System.out.println(singleNode.getName());
        System.out.println(singleNode.getStringValue());
        System.out.println(singleNode.getPath());
        System.out.println(singleNode.getNodeTypeName());
    }

    @Test
    public void xpathTest7() throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read("resources/book.xml");

        String xpath = "/bookstore";
        Node bookstore = document.selectSingleNode(xpath);
        System.out.println(bookstore.getPath());

        Node book = bookstore.selectSingleNode("/bookstore/book");
        System.out.println(book.getPath());
    }
}
