package com.zq.base;

import com.zq.utils.DruidPool;
import com.zq.utils.EmptyUtils;
import lombok.Getter;
import lombok.Setter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Description: 这是一个模块的Servlet基类, 使用反射机制来调用子类对象中与请求参数'methodName'的值
 * 同名的方法
 * @Author: zq007
 * @Date: 2021/7/1 14:10
 * @Version: V1.0
 */
public class BaseServlet extends HttpServlet {
    private static final long serialVersionUID = 179659019798033138L;

    /**
     * 本项目的数据库连接池
     */
    @Getter
    @Setter
    private DataSource dataSource;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 获得请求中的methodName参数
        String methodName = request.getParameter("methodName");

        if (!EmptyUtils.isEmpty(methodName)) {
            // 2. 使用反射机制来调用子类对象中名字跟methodName的只匹配的方法
            Class<? extends BaseServlet> clazz = this.getClass();
            try {
                Method method = clazz.getMethod(methodName,
                        HttpServletRequest.class,
                        HttpServletResponse.class);
                method.invoke(this,request,response);
            } catch (NoSuchMethodException e) {
                System.out.println("不存在'" + methodName + "'这个功能");
                e.printStackTrace();
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("request请求没有携带methodName参数");
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            dataSource= DruidPool.getInstance().getDataSource();
        } catch (Exception e) {
            System.out.println("与数据库建立连接失败");
            e.printStackTrace();
        }
    }
}
