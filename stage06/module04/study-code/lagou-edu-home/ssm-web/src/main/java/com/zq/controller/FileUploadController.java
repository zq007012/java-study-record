package com.zq.controller;

import com.zq.domain.ResponseResult;
import com.zq.utils.UUIDUtils;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/6/2 9:09
 */
@Controller("fileUploadController")
@ResponseBody
public class FileUploadController {

    @Autowired
    @Qualifier("comZq")
    private Logger logger;

    @RequestMapping({"/course/courseUpload",
            "/promotionAd/promotionAdUpload"})
    public ResponseResult imgUpload(@RequestParam(name = "file") MultipartFile multipartFile,
                                    HttpServletRequest request) {
        if (multipartFile.isEmpty()) {
            throw new RuntimeException("上传的文件为空");
        }

        try {
            //获取upload文件夹在服务器硬盘中的实际位置
            String contextRealPath = request.getServletContext().getRealPath("/");
            String uploadRealPath = contextRealPath.substring(0, contextRealPath.lastIndexOf(File.separator,
                    contextRealPath.length() - 2)) + File.separator + "upload";
            logger.debug("本应用在服务器中的根路径: " + contextRealPath);
            logger.debug("上传文件的保存路径: " + uploadRealPath);
            File uploadDir = new File(uploadRealPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            //将上传的文件接收到upload文件夹中
            String fileName = UUIDUtils.generateUUID() + "_" + multipartFile.getOriginalFilename();
            File destFile = new File(uploadDir, fileName);
            multipartFile.transferTo(destFile);
            //响应数据
            HashMap<String, String> content = new HashMap<>();
            content.put("fileName", destFile.getName());
            content.put("filePath", "http://localhost:8080/upload/" + fileName);
            return new ResponseResult(true, 200, "上传图片成功", content);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return null;
        }

    }
}
