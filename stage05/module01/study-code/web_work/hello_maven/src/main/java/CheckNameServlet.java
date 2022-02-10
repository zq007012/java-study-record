import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.HashMap;

/**
 * 检测请求里的'username'参数的值是否为'nami'.
 *
 * @author zq007
 * @version V1.0
 * @date 2022/2/9 18:58
 */
@WebServlet(name = "CheckNameServlet", urlPatterns ={"/checkName"})
public class CheckNameServlet extends HttpServlet{
    private static final long serialVersionUID = 7125575485985364716L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // 1. 解析请求
            req.setCharacterEncoding("utf-8");
            String username = req.getParameter("username").trim();
            // 2. 业务处理
            String resqData = null;
            HashMap<String, Object> map = new HashMap<String, Object>();
            if ("nami".equalsIgnoreCase(username)){
                map.put("flag",false);
                map.put("msg","用户名已存在, 不可使用!");
            }else{
                map.put("flag",true);
                map.put("msg","用户名可以使用");
            }

            String respData = JSON.toJSONString(map);

            //3. 进行响应
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write(respData);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
