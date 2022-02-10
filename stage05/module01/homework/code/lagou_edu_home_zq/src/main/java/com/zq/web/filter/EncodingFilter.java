package com.zq.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author : zq007
 * @date : 2022/2/3 17:43
 * @version : V1.0
 */

/**
 * 编码过滤器, 解决所有访问的请求乱码和响应乱码的问题
 *
 * @author zq007
 * @date 2022/2/3 17:43
 * @version V1.0
 */
@WebFilter(filterName = "EncodingFilter", urlPatterns = {"/*"})
public class EncodingFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        // 解决请求乱码
        req.setCharacterEncoding("utf-8");
        // 解决响应乱码
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        // 放行
        chain.doFilter(req, resp);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
