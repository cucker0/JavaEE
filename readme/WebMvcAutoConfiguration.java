//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.springframework.boot.autoconfigure.web.servlet;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Stream;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication.Type;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration;
import org.springframework.boot.autoconfigure.template.TemplateAvailabilityProviders;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.autoconfigure.validation.ValidatorAdapter;
import org.springframework.boot.autoconfigure.web.ConditionalOnEnabledResourceChain;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.WebProperties.Resources;
import org.springframework.boot.autoconfigure.web.WebProperties.Resources.Chain;
import org.springframework.boot.autoconfigure.web.WebProperties.Resources.Chain.Strategy;
import org.springframework.boot.autoconfigure.web.format.DateTimeFormatters;
import org.springframework.boot.autoconfigure.web.format.WebConversionService;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties.Contentnegotiation;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties.Format;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties.MatchingStrategy;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.filter.OrderedFormContentFilter;
import org.springframework.boot.web.servlet.filter.OrderedHiddenHttpMethodFilter;
import org.springframework.boot.web.servlet.filter.OrderedRequestContextFilter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.ClassUtils;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.PathExtensionContentNegotiationStrategy;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.ServletContextResource;
import org.springframework.web.filter.FormContentFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.filter.RequestContextFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ThemeResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceChainRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.resource.AppCacheManifestTransformer;
import org.springframework.web.servlet.resource.EncodedResourceResolver;
import org.springframework.web.servlet.resource.ResourceResolver;
import org.springframework.web.servlet.resource.ResourceUrlProvider;
import org.springframework.web.servlet.resource.VersionResourceResolver;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.util.UrlPathHelper;
import org.springframework.web.util.pattern.PathPatternParser;

@Configuration(
        proxyBeanMethods = false
)
@ConditionalOnWebApplication(
        type = Type.SERVLET
)
@ConditionalOnClass({Servlet.class, DispatcherServlet.class, WebMvcConfigurer.class})
@ConditionalOnMissingBean({WebMvcConfigurationSupport.class})
@AutoConfigureOrder(-2147483638)
@AutoConfigureAfter({DispatcherServletAutoConfiguration.class, TaskExecutionAutoConfiguration.class, ValidationAutoConfiguration.class})
public class WebMvcAutoConfiguration {
    public static final String DEFAULT_PREFIX = "";
    public static final String DEFAULT_SUFFIX = "";
    private static final String SERVLET_LOCATION = "/";

    public WebMvcAutoConfiguration() {
    }

    @Bean
    @ConditionalOnMissingBean({HiddenHttpMethodFilter.class})
    @ConditionalOnProperty(
            prefix = "spring.mvc.hiddenmethod.filter",
            name = {"enabled"},
            matchIfMissing = false
    )
    public OrderedHiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new OrderedHiddenHttpMethodFilter();
    }

    @Bean
    @ConditionalOnMissingBean({FormContentFilter.class})
    @ConditionalOnProperty(
            prefix = "spring.mvc.formcontent.filter",
            name = {"enabled"},
            matchIfMissing = true
    )
    public OrderedFormContentFilter formContentFilter() {
        return new OrderedFormContentFilter();
    }

    static class OptionalPathExtensionContentNegotiationStrategy implements ContentNegotiationStrategy {
        private static final String SKIP_ATTRIBUTE = PathExtensionContentNegotiationStrategy.class.getName() + ".SKIP";
        private final ContentNegotiationStrategy delegate;

        OptionalPathExtensionContentNegotiationStrategy(ContentNegotiationStrategy delegate) {
            this.delegate = delegate;
        }

        public List<MediaType> resolveMediaTypes(NativeWebRequest webRequest) throws HttpMediaTypeNotAcceptableException {
            Object skip = webRequest.getAttribute(SKIP_ATTRIBUTE, 0);
            return skip != null && Boolean.parseBoolean(skip.toString()) ? MEDIA_TYPE_ALL_LIST : this.delegate.resolveMediaTypes(webRequest);
        }
    }

    static class ResourceChainResourceHandlerRegistrationCustomizer implements WebMvcAutoConfiguration.ResourceHandlerRegistrationCustomizer {
        private final Resources resourceProperties;

        ResourceChainResourceHandlerRegistrationCustomizer(Resources resourceProperties) {
            this.resourceProperties = resourceProperties;
        }

        public void customize(ResourceHandlerRegistration registration) {
            Chain properties = this.resourceProperties.getChain();
            this.configureResourceChain(properties, registration.resourceChain(properties.isCache()));
        }

        private void configureResourceChain(Chain properties, ResourceChainRegistration chain) {
            Strategy strategy = properties.getStrategy();
            if (properties.isCompressed()) {
                chain.addResolver(new EncodedResourceResolver());
            }

            if (strategy.getFixed().isEnabled() || strategy.getContent().isEnabled()) {
                chain.addResolver(this.getVersionResourceResolver(strategy));
            }

            if (properties instanceof org.springframework.boot.autoconfigure.web.ResourceProperties.Chain && ((org.springframework.boot.autoconfigure.web.ResourceProperties.Chain)properties).isHtmlApplicationCache()) {
                chain.addTransformer(new AppCacheManifestTransformer());
            }

        }

        private ResourceResolver getVersionResourceResolver(Strategy properties) {
            VersionResourceResolver resolver = new VersionResourceResolver();
            if (properties.getFixed().isEnabled()) {
                String version = properties.getFixed().getVersion();
                String[] paths = properties.getFixed().getPaths();
                resolver.addFixedVersionStrategy(version, paths);
            }

            if (properties.getContent().isEnabled()) {
                String[] paths = properties.getContent().getPaths();
                resolver.addContentVersionStrategy(paths);
            }

            return resolver;
        }
    }

    interface ResourceHandlerRegistrationCustomizer {
        void customize(ResourceHandlerRegistration registration);
    }

    @Configuration(
            proxyBeanMethods = false
    )
    @ConditionalOnEnabledResourceChain
    static class ResourceChainCustomizerConfiguration {
        ResourceChainCustomizerConfiguration() {
        }

        @Bean
        WebMvcAutoConfiguration.ResourceChainResourceHandlerRegistrationCustomizer resourceHandlerRegistrationCustomizer(ResourceProperties resourceProperties, WebProperties webProperties) {
            return new WebMvcAutoConfiguration.ResourceChainResourceHandlerRegistrationCustomizer((Resources)(resourceProperties.hasBeenCustomized() ? resourceProperties : webProperties.getResources()));
        }
    }

    @Configuration(
            proxyBeanMethods = false
    )
    @EnableConfigurationProperties({WebProperties.class})
    public static class EnableWebMvcConfiguration extends DelegatingWebMvcConfiguration implements ResourceLoaderAware {
        private static final Log logger = LogFactory.getLog(WebMvcConfigurer.class);
        private final Resources resourceProperties;
        private final WebMvcProperties mvcProperties;
        private final WebProperties webProperties;
        private final ListableBeanFactory beanFactory;
        private final WebMvcRegistrations mvcRegistrations;
        private final WebMvcAutoConfiguration.ResourceHandlerRegistrationCustomizer resourceHandlerRegistrationCustomizer;
        private ResourceLoader resourceLoader;

        public EnableWebMvcConfiguration(ResourceProperties resourceProperties, WebMvcProperties mvcProperties, WebProperties webProperties, ObjectProvider<WebMvcRegistrations> mvcRegistrationsProvider, ObjectProvider<WebMvcAutoConfiguration.ResourceHandlerRegistrationCustomizer> resourceHandlerRegistrationCustomizerProvider, ListableBeanFactory beanFactory) {
            this.resourceProperties = (Resources)(resourceProperties.hasBeenCustomized() ? resourceProperties : webProperties.getResources());
            this.mvcProperties = mvcProperties;
            this.webProperties = webProperties;
            this.mvcRegistrations = (WebMvcRegistrations)mvcRegistrationsProvider.getIfUnique();
            this.resourceHandlerRegistrationCustomizer = (WebMvcAutoConfiguration.ResourceHandlerRegistrationCustomizer)resourceHandlerRegistrationCustomizerProvider.getIfAvailable();
            this.beanFactory = beanFactory;
        }

        @Bean
        public RequestMappingHandlerAdapter requestMappingHandlerAdapter(@Qualifier("mvcContentNegotiationManager") ContentNegotiationManager contentNegotiationManager, @Qualifier("mvcConversionService") FormattingConversionService conversionService, @Qualifier("mvcValidator") Validator validator) {
            RequestMappingHandlerAdapter adapter = super.requestMappingHandlerAdapter(contentNegotiationManager, conversionService, validator);
            adapter.setIgnoreDefaultModelOnRedirect(this.mvcProperties == null || this.mvcProperties.isIgnoreDefaultModelOnRedirect());
            return adapter;
        }

        protected RequestMappingHandlerAdapter createRequestMappingHandlerAdapter() {
            if (this.mvcRegistrations != null) {
                RequestMappingHandlerAdapter adapter = this.mvcRegistrations.getRequestMappingHandlerAdapter();
                if (adapter != null) {
                    return adapter;
                }
            }

            return super.createRequestMappingHandlerAdapter();
        }

        @Bean
        @Primary
        public RequestMappingHandlerMapping requestMappingHandlerMapping(@Qualifier("mvcContentNegotiationManager") ContentNegotiationManager contentNegotiationManager, @Qualifier("mvcConversionService") FormattingConversionService conversionService, @Qualifier("mvcResourceUrlProvider") ResourceUrlProvider resourceUrlProvider) {
            return super.requestMappingHandlerMapping(contentNegotiationManager, conversionService, resourceUrlProvider);
        }

        protected void addResourceHandlers(ResourceHandlerRegistry registry) {
            super.addResourceHandlers(registry);
            if (!this.resourceProperties.isAddMappings()) {
                logger.debug("Default resource handling disabled");
            } else {
                ServletContext servletContext = this.getServletContext();
                this.addResourceHandler(registry, "/webjars/**", "classpath:/META-INF/resources/webjars/");
                this.addResourceHandler(registry, this.mvcProperties.getStaticPathPattern(), (registration) -> {
                    registration.addResourceLocations(this.resourceProperties.getStaticLocations());
                    if (servletContext != null) {
                        registration.addResourceLocations(new Resource[]{new ServletContextResource(servletContext, "/")});
                    }

                });
            }
        }

        private void addResourceHandler(ResourceHandlerRegistry registry, String pattern, String... locations) {
            this.addResourceHandler(registry, pattern, (registration) -> {
                registration.addResourceLocations(locations);
            });
        }

        private void addResourceHandler(ResourceHandlerRegistry registry, String pattern, Consumer<ResourceHandlerRegistration> customizer) {
            if (!registry.hasMappingForPattern(pattern)) {
                ResourceHandlerRegistration registration = registry.addResourceHandler(new String[]{pattern});
                customizer.accept(registration);
                registration.setCachePeriod(this.getSeconds(this.resourceProperties.getCache().getPeriod()));
                registration.setCacheControl(this.resourceProperties.getCache().getCachecontrol().toHttpCacheControl());
                this.customizeResourceHandlerRegistration(registration);
            }
        }

        private Integer getSeconds(Duration cachePeriod) {
            return cachePeriod != null ? (int)cachePeriod.getSeconds() : null;
        }

        private void customizeResourceHandlerRegistration(ResourceHandlerRegistration registration) {
            if (this.resourceHandlerRegistrationCustomizer != null) {
                this.resourceHandlerRegistrationCustomizer.customize(registration);
            }

        }

        @Bean
        public WelcomePageHandlerMapping welcomePageHandlerMapping(ApplicationContext applicationContext, FormattingConversionService mvcConversionService, ResourceUrlProvider mvcResourceUrlProvider) {
            WelcomePageHandlerMapping welcomePageHandlerMapping = new WelcomePageHandlerMapping(new TemplateAvailabilityProviders(applicationContext), applicationContext, this.getWelcomePage(), this.mvcProperties.getStaticPathPattern());
            welcomePageHandlerMapping.setInterceptors(this.getInterceptors(mvcConversionService, mvcResourceUrlProvider));
            welcomePageHandlerMapping.setCorsConfigurations(this.getCorsConfigurations());
            return welcomePageHandlerMapping;
        }

        @Bean
        @ConditionalOnMissingBean(
                name = {"localeResolver"}
        )
        public LocaleResolver localeResolver() {
            if (this.webProperties.getLocaleResolver() == org.springframework.boot.autoconfigure.web.WebProperties.LocaleResolver.FIXED) {
                return new FixedLocaleResolver(this.webProperties.getLocale());
            } else if (this.mvcProperties.getLocaleResolver() == org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties.LocaleResolver.FIXED) {
                return new FixedLocaleResolver(this.mvcProperties.getLocale());
            } else {
                AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
                Locale locale = this.webProperties.getLocale() != null ? this.webProperties.getLocale() : this.mvcProperties.getLocale();
                localeResolver.setDefaultLocale(locale);
                return localeResolver;
            }
        }

        @Bean
        @ConditionalOnMissingBean(
                name = {"themeResolver"}
        )
        public ThemeResolver themeResolver() {
            return super.themeResolver();
        }

        @Bean
        @ConditionalOnMissingBean(
                name = {"flashMapManager"}
        )
        public FlashMapManager flashMapManager() {
            return super.flashMapManager();
        }

        private Resource getWelcomePage() {
            String[] var1 = this.resourceProperties.getStaticLocations();
            int var2 = var1.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                String location = var1[var3];
                Resource indexHtml = this.getIndexHtml(location);
                if (indexHtml != null) {
                    return indexHtml;
                }
            }

            ServletContext servletContext = this.getServletContext();
            if (servletContext != null) {
                return this.getIndexHtml((Resource)(new ServletContextResource(servletContext, "/")));
            } else {
                return null;
            }
        }

        private Resource getIndexHtml(String location) {
            return this.getIndexHtml(this.resourceLoader.getResource(location));
        }

        private Resource getIndexHtml(Resource location) {
            try {
                Resource resource = location.createRelative("index.html");
                if (resource.exists() && resource.getURL() != null) {
                    return resource;
                }
            } catch (Exception var3) {
            }

            return null;
        }

        @Bean
        public FormattingConversionService mvcConversionService() {
            Format format = this.mvcProperties.getFormat();
            WebConversionService conversionService = new WebConversionService((new DateTimeFormatters()).dateFormat(format.getDate()).timeFormat(format.getTime()).dateTimeFormat(format.getDateTime()));
            this.addFormatters(conversionService);
            return conversionService;
        }

        @Bean
        public Validator mvcValidator() {
            return !ClassUtils.isPresent("javax.validation.Validator", this.getClass().getClassLoader()) ? super.mvcValidator() : ValidatorAdapter.get(this.getApplicationContext(), this.getValidator());
        }

        protected RequestMappingHandlerMapping createRequestMappingHandlerMapping() {
            if (this.mvcRegistrations != null) {
                RequestMappingHandlerMapping mapping = this.mvcRegistrations.getRequestMappingHandlerMapping();
                if (mapping != null) {
                    return mapping;
                }
            }

            return super.createRequestMappingHandlerMapping();
        }

        protected ConfigurableWebBindingInitializer getConfigurableWebBindingInitializer(FormattingConversionService mvcConversionService, Validator mvcValidator) {
            try {
                return (ConfigurableWebBindingInitializer)this.beanFactory.getBean(ConfigurableWebBindingInitializer.class);
            } catch (NoSuchBeanDefinitionException var4) {
                return super.getConfigurableWebBindingInitializer(mvcConversionService, mvcValidator);
            }
        }

        protected ExceptionHandlerExceptionResolver createExceptionHandlerExceptionResolver() {
            if (this.mvcRegistrations != null) {
                ExceptionHandlerExceptionResolver resolver = this.mvcRegistrations.getExceptionHandlerExceptionResolver();
                if (resolver != null) {
                    return resolver;
                }
            }

            return super.createExceptionHandlerExceptionResolver();
        }

        protected void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
            super.extendHandlerExceptionResolvers(exceptionResolvers);
            if (this.mvcProperties.isLogResolvedException()) {
                Iterator var2 = exceptionResolvers.iterator();

                while(var2.hasNext()) {
                    HandlerExceptionResolver resolver = (HandlerExceptionResolver)var2.next();
                    if (resolver instanceof AbstractHandlerExceptionResolver) {
                        ((AbstractHandlerExceptionResolver)resolver).setWarnLogCategory(resolver.getClass().getName());
                    }
                }
            }

        }

        @Bean
        public ContentNegotiationManager mvcContentNegotiationManager() {
            ContentNegotiationManager manager = super.mvcContentNegotiationManager();
            List<ContentNegotiationStrategy> strategies = manager.getStrategies();
            ListIterator iterator = strategies.listIterator();

            while(iterator.hasNext()) {
                ContentNegotiationStrategy strategy = (ContentNegotiationStrategy)iterator.next();
                if (strategy instanceof PathExtensionContentNegotiationStrategy) {
                    iterator.set(new WebMvcAutoConfiguration.OptionalPathExtensionContentNegotiationStrategy(strategy));
                }
            }

            return manager;
        }

        public void setResourceLoader(ResourceLoader resourceLoader) {
            this.resourceLoader = resourceLoader;
        }
    }

    @Configuration(
            proxyBeanMethods = false
    )
    @Import({WebMvcAutoConfiguration.EnableWebMvcConfiguration.class})
    @EnableConfigurationProperties({WebMvcProperties.class, ResourceProperties.class, WebProperties.class})
    @Order(0)
    public static class WebMvcAutoConfigurationAdapter implements WebMvcConfigurer {
        private final WebMvcProperties mvcProperties;
        private final ListableBeanFactory beanFactory;
        private final ObjectProvider<HttpMessageConverters> messageConvertersProvider;
        private final ObjectProvider<DispatcherServletPath> dispatcherServletPath;
        private final ObjectProvider<ServletRegistrationBean<?>> servletRegistrations;
        final WebMvcAutoConfiguration.ResourceHandlerRegistrationCustomizer resourceHandlerRegistrationCustomizer;

        public WebMvcAutoConfigurationAdapter(WebProperties webProperties, WebMvcProperties mvcProperties, ListableBeanFactory beanFactory, ObjectProvider<HttpMessageConverters> messageConvertersProvider, ObjectProvider<WebMvcAutoConfiguration.ResourceHandlerRegistrationCustomizer> resourceHandlerRegistrationCustomizerProvider, ObjectProvider<DispatcherServletPath> dispatcherServletPath, ObjectProvider<ServletRegistrationBean<?>> servletRegistrations) {
            this.mvcProperties = mvcProperties;
            this.beanFactory = beanFactory;
            this.messageConvertersProvider = messageConvertersProvider;
            this.resourceHandlerRegistrationCustomizer = (WebMvcAutoConfiguration.ResourceHandlerRegistrationCustomizer)resourceHandlerRegistrationCustomizerProvider.getIfAvailable();
            this.dispatcherServletPath = dispatcherServletPath;
            this.servletRegistrations = servletRegistrations;
            this.mvcProperties.checkConfiguration();
        }

        public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
            this.messageConvertersProvider.ifAvailable((customConverters) -> {
                converters.addAll(customConverters.getConverters());
            });
        }

        public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
            if (this.beanFactory.containsBean("applicationTaskExecutor")) {
                Object taskExecutor = this.beanFactory.getBean("applicationTaskExecutor");
                if (taskExecutor instanceof AsyncTaskExecutor) {
                    configurer.setTaskExecutor((AsyncTaskExecutor)taskExecutor);
                }
            }

            Duration timeout = this.mvcProperties.getAsync().getRequestTimeout();
            if (timeout != null) {
                configurer.setDefaultTimeout(timeout.toMillis());
            }

        }

        public void configurePathMatch(PathMatchConfigurer configurer) {
            if (this.mvcProperties.getPathmatch().getMatchingStrategy() == MatchingStrategy.PATH_PATTERN_PARSER) {
                configurer.setPatternParser(new PathPatternParser());
            }

            configurer.setUseSuffixPatternMatch(this.mvcProperties.getPathmatch().isUseSuffixPattern());
            configurer.setUseRegisteredSuffixPatternMatch(this.mvcProperties.getPathmatch().isUseRegisteredSuffixPattern());
            this.dispatcherServletPath.ifAvailable((dispatcherPath) -> {
                String servletUrlMapping = dispatcherPath.getServletUrlMapping();
                if (servletUrlMapping.equals("/") && this.singleDispatcherServlet()) {
                    UrlPathHelper urlPathHelper = new UrlPathHelper();
                    urlPathHelper.setAlwaysUseFullPath(true);
                    configurer.setUrlPathHelper(urlPathHelper);
                }

            });
        }

        private boolean singleDispatcherServlet() {
            Stream var10000 = this.servletRegistrations.stream().map(ServletRegistrationBean::getServlet);
            DispatcherServlet.class.getClass();
            return var10000.filter(DispatcherServlet.class::isInstance).count() == 1L;
        }

        public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
            Contentnegotiation contentnegotiation = this.mvcProperties.getContentnegotiation();
            configurer.favorPathExtension(contentnegotiation.isFavorPathExtension());
            configurer.favorParameter(contentnegotiation.isFavorParameter());
            if (contentnegotiation.getParameterName() != null) {
                configurer.parameterName(contentnegotiation.getParameterName());
            }

            Map<String, MediaType> mediaTypes = this.mvcProperties.getContentnegotiation().getMediaTypes();
            mediaTypes.forEach(configurer::mediaType);
        }

        @Bean
        @ConditionalOnMissingBean
        public InternalResourceViewResolver defaultViewResolver() {
            InternalResourceViewResolver resolver = new InternalResourceViewResolver();
            resolver.setPrefix(this.mvcProperties.getView().getPrefix());
            resolver.setSuffix(this.mvcProperties.getView().getSuffix());
            return resolver;
        }

        @Bean
        @ConditionalOnBean({View.class})
        @ConditionalOnMissingBean
        public BeanNameViewResolver beanNameViewResolver() {
            BeanNameViewResolver resolver = new BeanNameViewResolver();
            resolver.setOrder(2147483637);
            return resolver;
        }

        @Bean
        @ConditionalOnBean({ViewResolver.class})
        @ConditionalOnMissingBean(
                name = {"viewResolver"},
                value = {ContentNegotiatingViewResolver.class}
        )
        public ContentNegotiatingViewResolver viewResolver(BeanFactory beanFactory) {
            ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
            resolver.setContentNegotiationManager((ContentNegotiationManager)beanFactory.getBean(ContentNegotiationManager.class));
            resolver.setOrder(-2147483648);
            return resolver;
        }

        public MessageCodesResolver getMessageCodesResolver() {
            if (this.mvcProperties.getMessageCodesResolverFormat() != null) {
                DefaultMessageCodesResolver resolver = new DefaultMessageCodesResolver();
                resolver.setMessageCodeFormatter(this.mvcProperties.getMessageCodesResolverFormat());
                return resolver;
            } else {
                return null;
            }
        }

        public void addFormatters(FormatterRegistry registry) {
            ApplicationConversionService.addBeans(registry, this.beanFactory);
        }

        @Bean
        @ConditionalOnMissingBean({RequestContextListener.class, RequestContextFilter.class})
        @ConditionalOnMissingFilterBean({RequestContextFilter.class})
        public static RequestContextFilter requestContextFilter() {
            return new OrderedRequestContextFilter();
        }
    }
}
