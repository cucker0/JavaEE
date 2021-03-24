package com.java.crudresful.component;

import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 解析 request 中的区域信息
 */
public class MyLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        String lang = httpServletRequest.getParameter("lang");
        System.out.println("===== lang: " + lang);
        Locale locale = Locale.getDefault();
        if (!StringUtils.isEmptyOrWhitespace(lang)) {
            String[] langSplit = lang.split("_");
            locale = new Locale(langSplit[0], langSplit[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
