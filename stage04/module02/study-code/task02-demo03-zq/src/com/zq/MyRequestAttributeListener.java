package com.zq;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.annotation.WebListener;

/**
 * @ClassName: MyRequestAttributeListener
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/6/12 9:08
 * @Version: V1.0
 */
@WebListener()
public class MyRequestAttributeListener implements ServletRequestAttributeListener{

    @Override
    public void attributeAdded(ServletRequestAttributeEvent servletRequestAttributeEvent) {

    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent servletRequestAttributeEvent) {

    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent servletRequestAttributeEvent) {

    }
}
