package com.lagou.base;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {

    /*
     * doGET方法作为一个调度器 .根据请求功能的不同,调用对应的方法
     *      规定必须传递一个参数
     *           methodName=功能名
     * */

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1.获取参数 要访问的方法名
        String methodName = req.getParameter("methodName");

        //2.判断 执行对应的方法
        if(methodName != null){
            //通过反射优化代码 提升代码的可维护性

            try {
                //1.获取字节码文件对象
                Class c = this.getClass();

                //2.根据传入的方法名,获取对应的方法对象  findByName
                Method method = c.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);

                //3.调用method对象的 invoke方法,执行对应的功能
                method.invoke(this,req,resp);

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("请求的功能不存在!!");
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
