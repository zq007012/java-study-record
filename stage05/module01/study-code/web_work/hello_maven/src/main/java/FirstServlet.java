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
 * @date 2022/1/28 18:25
 */
@WebServlet(name = "FirstServlet", urlPatterns = {"/firstServlet"})
public class FirstServlet extends HttpServlet {

    private static final long serialVersionUID = 417011812850948618L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        try {
            resp.getWriter().println("<html>\n" +
                    "<head>\n" +
                    "    <title>首页</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    <h1>这是我的第一个Maven项目的Servlet</h1>\n" +
                    "</body>\n" +
                    "</html>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
