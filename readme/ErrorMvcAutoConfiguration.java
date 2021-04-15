//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.springframework.boot.autoconfigure.web.servlet.error;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.framework.autoproxy.AutoProxyUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionMessage;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.boot.autoconfigure.condition.ConditionMessage.Builder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication.Type;
import org.springframework.boot.autoconfigure.template.TemplateAvailabilityProvider;
import org.springframework.boot.autoconfigure.template.TemplateAvailabilityProviders;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.WebProperties.Resources;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletPath;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.util.HtmlUtils;

@Configuration(
        proxyBeanMethods = false
)
@ConditionalOnWebApplication(
        type = Type.SERVLET
)
@ConditionalOnClass({Servlet.class, DispatcherServlet.class})
@AutoConfigureBefore({WebMvcAutoConfiguration.class})
@EnableConfigurationProperties({ServerProperties.class, WebMvcProperties.class})
public class ErrorMvcAutoConfiguration {
    private final ServerProperties serverProperties;

    public ErrorMvcAutoConfiguration(ServerProperties serverProperties) {
        this.serverProperties = serverProperties;
    }

    @Bean
    @ConditionalOnMissingBean(
            value = {ErrorAttributes.class},
            search = SearchStrategy.CURRENT
    )
    public DefaultErrorAttributes errorAttributes() {
        return new DefaultErrorAttributes();
    }

    @Bean
    @ConditionalOnMissingBean(
            value = {ErrorController.class},
            search = SearchStrategy.CURRENT
    )
    public BasicErrorController basicErrorController(ErrorAttributes errorAttributes, ObjectProvider<ErrorViewResolver> errorViewResolvers) {
        return new BasicErrorController(errorAttributes, this.serverProperties.getError(), (List)errorViewResolvers.orderedStream().collect(Collectors.toList()));
    }

    @Bean
    public ErrorMvcAutoConfiguration.ErrorPageCustomizer errorPageCustomizer(DispatcherServletPath dispatcherServletPath) {
        return new ErrorMvcAutoConfiguration.ErrorPageCustomizer(this.serverProperties, dispatcherServletPath);
    }

    @Bean
    public static ErrorMvcAutoConfiguration.PreserveErrorControllerTargetClassPostProcessor preserveErrorControllerTargetClassPostProcessor() {
        return new ErrorMvcAutoConfiguration.PreserveErrorControllerTargetClassPostProcessor();
    }

    static class PreserveErrorControllerTargetClassPostProcessor implements BeanFactoryPostProcessor {
        PreserveErrorControllerTargetClassPostProcessor() {
        }

        public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
            String[] errorControllerBeans = beanFactory.getBeanNamesForType(ErrorController.class, false, false);
            String[] var3 = errorControllerBeans;
            int var4 = errorControllerBeans.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                String errorControllerBean = var3[var5];

                try {
                    beanFactory.getBeanDefinition(errorControllerBean).setAttribute(AutoProxyUtils.PRESERVE_TARGET_CLASS_ATTRIBUTE, Boolean.TRUE);
                } catch (Throwable var8) {
                }
            }

        }
    }

    static class ErrorPageCustomizer implements ErrorPageRegistrar, Ordered {
        private final ServerProperties properties;
        private final DispatcherServletPath dispatcherServletPath;

        protected ErrorPageCustomizer(ServerProperties properties, DispatcherServletPath dispatcherServletPath) {
            this.properties = properties;
            this.dispatcherServletPath = dispatcherServletPath;
        }

        public void registerErrorPages(ErrorPageRegistry errorPageRegistry) {
            ErrorPage errorPage = new ErrorPage(this.dispatcherServletPath.getRelativePath(this.properties.getError().getPath()));
            errorPageRegistry.addErrorPages(new ErrorPage[]{errorPage});
        }

        public int getOrder() {
            return 0;
        }
    }

    private static class StaticView implements View {
        private static final MediaType TEXT_HTML_UTF8;
        private static final Log logger;

        private StaticView() {
        }

        public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
            if (response.isCommitted()) {
                String message = this.getMessage(model);
                logger.error(message);
            } else {
                response.setContentType(TEXT_HTML_UTF8.toString());
                StringBuilder builder = new StringBuilder();
                Object timestamp = model.get("timestamp");
                Object message = model.get("message");
                Object trace = model.get("trace");
                if (response.getContentType() == null) {
                    response.setContentType(this.getContentType());
                }

                builder.append("<html><body><h1>Whitelabel Error Page</h1>").append("<p>This application has no explicit mapping for /error, so you are seeing this as a fallback.</p>").append("<div id='created'>").append(timestamp).append("</div>").append("<div>There was an unexpected error (type=").append(this.htmlEscape(model.get("error"))).append(", status=").append(this.htmlEscape(model.get("status"))).append(").</div>");
                if (message != null) {
                    builder.append("<div>").append(this.htmlEscape(message)).append("</div>");
                }

                if (trace != null) {
                    builder.append("<div style='white-space:pre-wrap;'>").append(this.htmlEscape(trace)).append("</div>");
                }

                builder.append("</body></html>");
                response.getWriter().append(builder.toString());
            }
        }

        private String htmlEscape(Object input) {
            return input != null ? HtmlUtils.htmlEscape(input.toString()) : null;
        }

        private String getMessage(Map<String, ?> model) {
            Object path = model.get("path");
            String message = "Cannot render error page for request [" + path + "]";
            if (model.get("message") != null) {
                message = message + " and exception [" + model.get("message") + "]";
            }

            message = message + " as the response has already been committed.";
            message = message + " As a result, the response may have the wrong status code.";
            return message;
        }

        public String getContentType() {
            return "text/html";
        }

        static {
            TEXT_HTML_UTF8 = new MediaType("text", "html", StandardCharsets.UTF_8);
            logger = LogFactory.getLog(ErrorMvcAutoConfiguration.StaticView.class);
        }
    }

    private static class ErrorTemplateMissingCondition extends SpringBootCondition {
        private ErrorTemplateMissingCondition() {
        }

        public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
            Builder message = ConditionMessage.forCondition("ErrorTemplate Missing", new Object[0]);
            TemplateAvailabilityProviders providers = new TemplateAvailabilityProviders(context.getClassLoader());
            TemplateAvailabilityProvider provider = providers.getProvider("error", context.getEnvironment(), context.getClassLoader(), context.getResourceLoader());
            return provider != null ? ConditionOutcome.noMatch(message.foundExactly("template from " + provider)) : ConditionOutcome.match(message.didNotFind("error template view").atAll());
        }
    }

    @Configuration(
            proxyBeanMethods = false
    )
    @ConditionalOnProperty(
            prefix = "server.error.whitelabel",
            name = {"enabled"},
            matchIfMissing = true
    )
    @Conditional({ErrorMvcAutoConfiguration.ErrorTemplateMissingCondition.class})
    protected static class WhitelabelErrorViewConfiguration {
        private final ErrorMvcAutoConfiguration.StaticView defaultErrorView = new ErrorMvcAutoConfiguration.StaticView();

        protected WhitelabelErrorViewConfiguration() {
        }

        @Bean(
                name = {"error"}
        )
        @ConditionalOnMissingBean(
                name = {"error"}
        )
        public View defaultErrorView() {
            return this.defaultErrorView;
        }

        @Bean
        @ConditionalOnMissingBean
        public BeanNameViewResolver beanNameViewResolver() {
            BeanNameViewResolver resolver = new BeanNameViewResolver();
            resolver.setOrder(2147483637);
            return resolver;
        }
    }

    @Configuration(
            proxyBeanMethods = false
    )
    @EnableConfigurationProperties({ResourceProperties.class, WebProperties.class, WebMvcProperties.class})
    static class DefaultErrorViewResolverConfiguration {
        private final ApplicationContext applicationContext;
        private final Resources resources;

        DefaultErrorViewResolverConfiguration(ApplicationContext applicationContext, ResourceProperties resourceProperties, WebProperties webProperties) {
            this.applicationContext = applicationContext;
            this.resources = (Resources)(webProperties.getResources().hasBeenCustomized() ? webProperties.getResources() : resourceProperties);
        }

        @Bean
        @ConditionalOnBean({DispatcherServlet.class})
        @ConditionalOnMissingBean({ErrorViewResolver.class})
        DefaultErrorViewResolver conventionErrorViewResolver() {
            return new DefaultErrorViewResolver(this.applicationContext, this.resources);
        }
    }
}
