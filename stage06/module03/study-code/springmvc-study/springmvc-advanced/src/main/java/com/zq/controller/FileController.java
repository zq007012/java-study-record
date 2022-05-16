package com.zq.controller;

import com.zq.domain.Account;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/5/4 9:05
 */
@Controller
public class FileController {
    /**
     * @param account account的成员变量会接收表单中对应的键值对
     * @param filePic filePic, 表示会将表单中控件名为filePic的上传文件封装到MultipartFile对象中
     * @return
     * @throws IOException
     */
    @RequestMapping("/singleFile")
    public String singleFile(Account account, MultipartFile filePic) throws IOException {
        System.out.println(account);

        String filename = filePic.getOriginalFilename();
        InputStream inputStream = filePic.getInputStream();
        FileOutputStream fos1 = new FileOutputStream(new File("d:/file1.gif"));
        FileOutputStream fos2 = new FileOutputStream(new File("e:/file1.gif"));
        byte[] buffer = new byte[4096];
        int len = 0;
        while ((len = inputStream.read(buffer)) > 0) {
            fos1.write(buffer, 0, len);
            fos2.write(buffer, 0, len);
        }
        fos1.close();
        fos2.close();
        inputStream.close();
        return "success";
    }

    @RequestMapping("/multipleFiles")
    public String multipleFiles(Account account, MultipartFile[] filePics) throws IOException {
        System.out.println(account);
        for (MultipartFile filePic : filePics) {
            filePic.transferTo(new File("d:/" + filePic.getOriginalFilename()));
        }
        return "success";
    }

    @RequestMapping("/errorTest")
    public String errorTest(){
        int i = 1 / 0;
        return "success";
    }
}
