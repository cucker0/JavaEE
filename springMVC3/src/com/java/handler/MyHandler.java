package com.java.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

@Controller
public class MyHandler {
    @Autowired
    private ResourceBundleMessageSource messageSource;

    // 获取客户端的请求体内容
    @ResponseBody
    @RequestMapping("/testHttpMessageConverter")
    public String testHttpMessageConverter(@RequestBody String body) {
        System.out.println(body);
        return "hi gay, " + new Date();
    }

    // 下载文件
    @RequestMapping("/testResponseEntiry")
    public ResponseEntity<byte[]> testResponseEntiry(HttpSession session) throws IOException {
        byte[] body = null;
        ServletContext servletContext = session.getServletContext();
        InputStream in = servletContext.getResourceAsStream("/file/masu1.png");
        body = new byte[in.available()];
        in.read(body);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "attachment;filename=masu1.png");
        HttpStatus statusCode = HttpStatus.OK;
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(body, httpHeaders, statusCode);
        return responseEntity;
    }

    // 国际化
    @RequestMapping("/i18n")
    public String testI18n(Locale locale) {
        System.out.println(locale);
        String val = messageSource.getMessage("i18n.username", null, locale);
        System.out.println(val);
        return "i18n_page";
    }

    // 上传文件, CommonsMultipartResolver
    @RequestMapping("upload")
    public String testFileUpload(@RequestParam("file")MultipartFile file, HttpServletRequest req) {
        if (file.isEmpty()) {
            return "file-upload-failed";
        }
        // 获取文件存储路径（绝对路径）
        LocalDate localDate = LocalDate.now();
        String path = req.getServletContext().getRealPath("/upload/" + localDate.getYear() + localDate.getMonthValue() + localDate.getDayOfMonth());
        System.out.println(file.getOriginalFilename());
        String[] originalFilenameSplit = file.getOriginalFilename().split("\\.");
        String fileName = UUID.randomUUID().toString() + "." +  originalFilenameSplit[originalFilenameSplit.length - 1];
        // 创建文件实例
        File filePath = new File(path, fileName);
        // 如果文件目录不存在，创建目录
        if (!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录" + filePath.getParentFile());
        }
        // 写入文件
        try {
            file.transferTo(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "file-upload-success";
    }
}
