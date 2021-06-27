package com.zq.controller.filter;

import com.zq.controller.servlet.WebConstant;
import com.zq.model.factory.UserDaoFactory;
import com.zq.model.javabean.User;
import com.zq.model.service.UserManagerService;
import com.zq.utils.EmptyUtils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class VerifyLogInFilter implements Filter {
    /**
     * 过滤时需要排除的servlet
     */
    private String excludeFilterPath;

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String requestServletPath = request.getServletPath();
        // 排除掉包含lib的请求路径, 以及排除存在于excludeFilterPath中的requestServletPath
        System.out.println("将要访问" + requestServletPath + "...");
        if (request.getRequestURI().contains("/lib/") ||
                excludeFilterPath.contains(requestServletPath)){
            System.out.println("在排除过滤列表内, 直接放行");
            chain.doFilter(req,resp);
            return;
        }
        // 1. 当前会话是个不是新会话时, 判断当前用户的登录状态, 已登录就放行, 否则重定向到登录界面
        HttpSession session = request.getSession();
        if (!session.isNew()) {
            // 2. 当前会话不是新会话时, 通过保存在session中的登录状态属性来判断用户是否登录成功
            // 没有登录成功就重定向到登录界面, 否则放行
            System.out.println("当前会话时个旧session");
            String logInStatus = (String) session.getAttribute(WebConstant.LOGIN_STATUS);
            if (!WebConstant.SUCCESS.equals(logInStatus)) {
                System.out.println("当前用户未登录, 重定向到登录界面");
                response.sendRedirect("login.jsp");
            }else{
                System.out.println("当前用户已登录, 放行...");
                chain.doFilter(req, resp);
            }
        }else{
            //3. 当不是个新会话时, 就从该用户的cookie中判断该用户是否在一周内登录过
            try {
                System.out.println("当前会话是个新session");
                checkSessionIdFromCookie(request, response, chain);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 通过发出请求的浏览器的cookie中保存的sessionId来判断该用户是否在7天之内成功
     * 登录过, 如果是则放行, 否则重定向到登录界面
     *
     * @param request
     * @param response
     * @param chain
     */
    private void checkSessionIdFromCookie(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws Exception {
        // 1. 获取cookie中封装的sessionId
        Cookie[] cookies = request.getCookies();
        // cookie为空, 跳转到登录界面
        if (EmptyUtils.isEmpty(cookies)) {
            System.out.println("没有包含sessionId的cookie, 跳转到登录界面");
            response.sendRedirect("login.jsp");
            return;
        }

        String sessionIdFromCookie = null;
        for (Cookie cookie :
                cookies) {
            if (WebConstant.SESSION_ID.equals(cookie.getName())) {
                sessionIdFromCookie = cookie.getValue();
                break;
            }
        }

        // 没有包含sessionId的cookie, 跳转到登录界面
        if (null == sessionIdFromCookie) {
            System.out.println("没有包含sessionId的cookie, 跳转到登录界面");
            response.sendRedirect("login.jsp");
            return;
        }

        /**
         * 检测user表中是否有包含该sessionId的记录, 如果没有则重定向到登录界面,
         * 如果有则判断是否是在7天之内登录的
         */
        System.out.println("cookie中的sessionId是" + sessionIdFromCookie);
        UserManagerService manager = new UserManagerService(UserDaoFactory.newUserDao());
        User user = manager.hadLoggedIn(sessionIdFromCookie);
        if (null == user) {
            System.out.println("该sessionId与七天内免登录的sessionId不匹配, 重定向到登录界面");
            response.sendRedirect("login.jsp");
            return;
        }

        /**
         * 判断该用户是否是在7天之内登陆的, 大于7天,则将登录状态改为invalid, 然后重定向到登录界面
         * 否则, 将登录状态改为success, 将user对象保存到session的属性中, 然后放行
         */
        System.out.println("上次登录成功的日期是:" + user.getDateOfLastMarkLoginSuccessfully());
        LocalDate dateOfLastLogIn = LocalDate.parse(user.getDateOfLastMarkLoginSuccessfully(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        int dayOfDuration = (int) dateOfLastLogIn.until(LocalDate.now(), ChronoUnit.DAYS);
        if (dayOfDuration > 7) {
            System.out.println("该sessionId与七天内免登录的sessionId相匹配, 但已经超过了7天, 重定向到登录界面");
            request.getSession().setAttribute(WebConstant.LOGIN_STATUS,
                    WebConstant.INVALID);
            response.sendRedirect("login.jsp");
        } else {
            System.out.println("该sessionId与七天内免登录的sessionId相匹配, 并且在7天之内, 重定向到主页");
            request.getSession().setAttribute(WebConstant.LOGIN_STATUS,
                    WebConstant.SUCCESS);
            request.getSession().setAttribute(WebConstant.USER, user);
            response.sendRedirect("index.jsp");
        }

    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        excludeFilterPath = config.getInitParameter(WebConstant.EXCLUDE_FILTER_PATH);
        System.out.println(excludeFilterPath);
    }

}
