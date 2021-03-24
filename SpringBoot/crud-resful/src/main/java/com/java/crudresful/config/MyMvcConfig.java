package com.java.crudresful.config;

import com.java.crudresful.component.MyLocaleResolver;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    /**
     * 添加视图
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
    }

    /**
     * 添加一个 Locale区域解析器
     */
    @Bean(name = "localeResolver")
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }


    // ==== 把locale信息设置在cookie中 start
    // 这是另一种设置设置国际化的方式
    // @Bean
    // public LocaleResolver localeResolver() {
    //     CookieLocaleResolver localeResolver = new CookieLocaleResolver();
    //     localeResolver.setCookieName("localeCookie");
    //     //设置默认区域
    //     localeResolver.setDefaultLocale(Locale.ENGLISH);
    //     localeResolver.setCookieMaxAge(3600);//设置cookie有效期.
    //     return localeResolver;
    // }
    //
    // /**
    //  * 改变locale的拦截器
    //  * @return
    //  */
    // @Bean
    // public LocaleChangeInterceptor localeChangeInterceptor() {
    //     LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
    //     localeChangeInterceptor.setParamName("lang");
    //     return localeChangeInterceptor;
    // }
    //
    // @Override
    // public void addInterceptors(InterceptorRegistry registry) {
    //     registry.addInterceptor(localeChangeInterceptor());
    // }
    // ==== 把locale信息设置在cookie中 end
}
