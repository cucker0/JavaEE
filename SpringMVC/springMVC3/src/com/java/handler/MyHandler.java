package com.java.handler;

import com.java.ExceptionHandler.UserNotMatchExcption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping("testExceptionHandlerExceptionResolver")
    public String testExceptionHandlerExceptionResolver(@RequestParam(value = "num") int i) {
        System.out.println("10 / i: = " + 10 / i);
        return "success";
    }

    /**
     * 处理异常，只在本Handler中生效
     *
     * 1. 在 @ExceptionHandler 方法的入参中可以加入 Exception 类型的参数, 该参数即对应发生的异常对象
     * 2. @ExceptionHandler 方法的入参中不能传入 Map. 若希望把异常信息传导页面上, 需要使用 ModelAndView 作为返回值
     * 3. @ExceptionHandler 方法标记的异常有优先级的问题.
     * 4. @ControllerAdvice: 如果在当前 Handler 中找不到 @ExceptionHandler 方法来出来当前方法出现的异常,
     * 则将去 @ControllerAdvice 标记的类中查找 @ExceptionHandler 标记的方法来处理异常.
     *
     * @param e
     * @return
     */
    @ExceptionHandler({ArithmeticException.class})
    public ModelAndView handlerArithmeticException(Exception e) {
        System.out.println("[ArithmeticException]出异常了：" + e);
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("exception", e);
        return mv;
    }
    //
    // @ExceptionHandler({RuntimeException.class})
    // public ModelAndView handlerArithmeticException2(Exception e) {
    //     System.out.println("[RuntimeException]出异常了：" + e);
    //     ModelAndView mv = new ModelAndView("error");
    //     mv.addObject("exception", e);
    //     return mv;
    // }

    // ResponseStatusExcptionResover
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "看什么呢？？？")  // 不报异常的正常请求返回这里的HttpStatus和提示消息，但方法仍能正常执行，其他的报异常类上的
    @RequestMapping("testResponseStatusExcptionResover")
    public String testResponseStatusExcptionResover(@RequestParam("username") String username, @RequestParam("code") int code ) {
        if (!username.equals("admin") ) {
            throw new UserNotMatchExcption();
        }
        if (code != 3344) {
            throw new UserNotMatchExcption();
        }
        System.out.println("test ResponseStatusExcptionResover ... ");
        return "success";
    }

    // DefaultHandlerExceptionResolver
    @RequestMapping(value = "/testDefaultHandlerExceptionResolver", method = RequestMethod.POST)
    public String testDefaultHandlerExceptionResolver() {
        System.out.println("testDefaultHandlerExceptionResolver ...");
        return "success";
    }

    // SimpleMappingExceptionResolver，需要在SpringMVC 配置SimpleMappingExceptionResolver 来映射异常
    @RequestMapping("testSimpleMappingExceptionResolver")
    public String testSimpleMappingExceptionResolver(@RequestParam("index") int i) {
        System.out.println("SimpleMappingExceptionResolver ...");
        int[] arr = new int[]{1, 2};
        /*
        java.lang.ArrayIndexOutOfBoundsException: Index 4 out of bounds for length 2
         */
        System.out.println(arr[i]);
        return "success";
    }
}
