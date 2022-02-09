package com.zq.base;

import com.alibaba.fastjson.JSON;
import com.zq.utils.DruidPool;
import com.zq.utils.EmptyUtils;
import lombok.Getter;
import lombok.Setter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

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

    /**
     * 数据库连接池对象, 方便子类的各种功能的使用
     */
    @Getter
    @Setter
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        try {
            dataSource = DruidPool.getInstance().getDataSource();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //已经在EncodingFilter中对req的编码进行了设置, 这里就不必再设置了
        //req.setCharacterEncoding("utf-8");
        //String contentType = req.getHeader("Content-Type");
        String contentType = req.getContentType();
        String methodName = null;
        if ("application/json;charset=utf-8".equalsIgnoreCase(contentType)) {
            String postJson = getPostJson(req);
            Map<String,Object> postJsonMap = JSON.parseObject(postJson, Map.class);
            req.setAttribute("postJsonMap",postJsonMap);

            assert postJsonMap != null;
            methodName = (String) postJsonMap.get("methodName");
        } else {
            methodName = req.getParameter("methodName");
        }

        if (!EmptyUtils.isEmpty(methodName)) {
            Class<? extends BaseServlet> clazz = this.getClass();
            try {
                Method method = clazz.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
                method.invoke(this, req, resp);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                System.out.println("[" + methodName + "]这个功能不存在, 或者这个功能里出现异常");
                e.printStackTrace();
            }
        } else {
            System.out.println("request请求没有携带methodName参数");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    /**
     * 当post请求将表单中的数据以json格式传送到后台时, 可以通过此方法读取post请求中
     * 的json格式的字符串
     *
     * @param req
     * @return
     */
    private String getPostJson(HttpServletRequest req) {
        try {
            BufferedReader reader = req.getReader();
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
