package com.zq.base;

import com.zq.utils.EmptyUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 创建一个Servlet的基类, 这个基类是抽象类, 在这个类的doGet方法中, 获取请求中的methodName
 * 参数, 使用反射机制和这个methodName参数来调用本模块的相应功能
 *
 * @author zq007
 * @version V1.0
 * @date 2022/2/3 17:33
 */
public abstract class BaseServlet extends HttpServlet {

    private static final long serialVersionUID = 7088995230048133435L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //已经在EncodingFilter中对req的编码进行了设置, 这里就不必再设置了
        //req.setCharacterEncoding("utf-8");
        String methodName = req.getParameter("methodName");
        if (!EmptyUtils.isEmpty(methodName)) {
            Class<? extends BaseServlet> clazz = this.getClass();
            try {
                Method method = clazz.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
                method.invoke(this, req, resp);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                System.out.println("不存在[" + methodName + "]这个功能");
                //e.printStackTrace();
            }
        } else {
            System.out.println("request请求没有携带methodName参数");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
