springMVC3
==

## i18n国际化
```text
关于国际化:
1. 在页面上能够根据浏览器语言设置的情况对文本(不是内容), 时间, 数值进行本地化处理
2. 可以在 bean 中获取国际化资源文件 Locale 对应的消息
3. 可以通过超链接切换 Locale, 而不再依赖于浏览器的语言设置情况

解决:
1. 使用 JSTL 的 fmt 标签
2. 在 bean 中注入 ResourceBundleMessageSource 的示例, 使用其对应的 getMessage 方法即可
3. 配置 LocalResolver 和 LocaleChangeInterceptor

```

## springmvc上传文件,
```text
CommonsMultipartResolver
```

## 自定义拦截器
