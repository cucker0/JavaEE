package com.java.springbootdatamybatis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @GetMapping("/")
    public String index() {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Index</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div>\n" +
                "        <li>\n" +
                "            <a href=\"/department\">Department</a>\n" +
                "        </li>\n" +
                "        <li>\n" +
                "            <a href=\"/employee\">Employee</a>\n" +
                "        </li>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";
    }
}
