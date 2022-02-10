import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/2/9 10:36
 */
@WebServlet(name = "LoginServlet", urlPatterns ={"/login"})
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = -6149840476654716708L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1.获取请求数据
        String username = req.getParameter("username");

        //打印 username
        System.out.println(username);
        resp.setContentType("text/text;charset=utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().write(username);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
