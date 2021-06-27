package com.zq.tag;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * @ClassName: OutTag
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/5/24 10:45
 * @Version: V1.0
 */
public class OutTag extends SimpleTagSupport{
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspContext jspContext = this.getJspContext();
        JspWriter out = jspContext.getOut();
        out.println(value);
    }
}
