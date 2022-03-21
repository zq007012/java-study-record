package com.zq.web.servlet;

import com.zq.base.Constant;
import com.zq.dao.CourseDao;
import com.zq.dao.impl.CourseDaoImpl;
import com.zq.pojo.Course;
import com.zq.service.CourseService;
import com.zq.service.impl.CourseServiceImpl;
import com.zq.utils.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.list.NodeCachingLinkedList;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 接收课程营销信息表单的Servlet, 可以保存课程营销信息或者更新课程营销信息, 通过判断
 * 请求中是否携带`id`这个参数来决定是保存课程营销信息,还是更新课程营销信息
 *
 * @author zq007
 * @version V1.0
 * @date 2022/2/5 16:18
 */
@WebServlet(name = "CourseSaleInfoServlet", urlPatterns ={"/courseSalesInfo"})
public class CourseSalesInfoServlet extends HttpServlet{

    private static final long serialVersionUID = 545203547345018070L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 一. 解析请求数据
        // 请求发送到了这里, 就说明这个请求是一个上传文件的表单
        // 1. 创建磁盘表单项工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 2. 创建文件上传核心类
        ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
        // 设置上传文件的文件名的编码, 避免读取文件名时出现乱码
        servletFileUpload.setHeaderEncoding("utf-8");
        try {
            // 3. 解析请求, 获取表单项集合
            List<FileItem> fileItems = servletFileUpload.parseRequest(req);
            // 4. 遍历表单项集合
            Map<String,String> map = new HashMap<>();
            for (FileItem fileItem :
                    fileItems) {
                // 5. 判断是普通表单项, 还是含有文件的表单项
                if(fileItem.isFormField()){
                    map.put(fileItem.getFieldName(),
                            fileItem.getString("utf-8"));
                }else{
                    InputStream inputStream = fileItem.getInputStream();

                    String realPath = this.getServletContext().getRealPath("/");
                    String rootPath = realPath.substring(0,
                            realPath.lastIndexOf(req.getContextPath().substring(1)));
                    // req.getContextPath() : "/lagou_edu_home_zq"
                    File uploadDir = new File(rootPath + "upload");
                    if (!uploadDir.exists()){
                        uploadDir.mkdirs();
                    }

                    File file = new File(uploadDir,
                            UUIDUtils.generateUUID() + "_" + fileItem.getName());

                    FileOutputStream fos = new FileOutputStream(file);

                    IOUtils.copy(inputStream,fos);

                    CloseUtils.closeResources(fos, inputStream);

                    map.put("course_img_url",
                            Constant.UPLOAD_URL + "/" + file.getName());
                }
            }

            // 二. 业务处理
            Course course = new Course();
            BeanUtils.populate(course,map);
            CourseDao courseDao = new CourseDaoImpl(DruidPool.getInstance().getDataSource());
            CourseService courseService = new CourseServiceImpl(courseDao);
            String result = null;
            String nowDateTime = DateTimeUtils.getDateTime();

            // 通过判断请求中是否携带`id`这个参数来决定是保存课程营销信息,还是更新课程营销信息
            if(EmptyUtils.isEmpty(map.get("id"))){
                //request的参数中没有携带`id`, 保存课程营销信息
                // 完善course的数据
                course.setStatus(1);
                course.setCreate_time(nowDateTime);
                course.setUpdate_time(nowDateTime);
                result = courseService.saveCourseSaleInfo(course);
            }else{
                //request的参数中有携带`id`, 更新课程营销信息
                course.setUpdate_time(nowDateTime);
                result = courseService.updateCourseSaleInfo(course);
            }



            // 三. 响应结果
            resp.getWriter().println(result);

        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
