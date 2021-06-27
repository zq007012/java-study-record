package com.zq;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @ClassName: RequestCountAndOnline
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/6/13 14:37
 * @Version: V1.0
 */
@WebListener
public class RequestCountAndOnlineCount implements ServletRequestListener,
        HttpSessionListener, ServletContextListener {
    private ServletContext context;
    private int requestCount;
    private int onlineCount;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        this.context = servletContextEvent.getServletContext();
        requestCount = 0;
        onlineCount = 0;
        setRequestCountAndOnlineCount();
    }

    private void setRequestCountAndOnlineCount() {
        setRequestCount();
        setOnlineCount();
    }

    /**
     * 设置在线人数
     */
    private void setOnlineCount() {
        context.setAttribute("onlineCount", onlineCount);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {

    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        // 新的请求产生, 访问次数加1
        requestCount += 1;
        setRequestCount();
    }

    /**
     * 设置Web应用的被访问次数
     */
    private void setRequestCount() {
        context.setAttribute("requestCount", requestCount);
    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        //新的session产生, 在线人数加1
        onlineCount += 1;
        setOnlineCount();
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        //有session销毁了, 说明有人下线了, 在线人数-1
        onlineCount -=1;
        setOnlineCount();
    }
}
