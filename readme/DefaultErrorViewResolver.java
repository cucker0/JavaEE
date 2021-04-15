//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.springframework.boot.autoconfigure.web.servlet.error;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.template.TemplateAvailabilityProvider;
import org.springframework.boot.autoconfigure.template.TemplateAvailabilityProviders;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.WebProperties.Resources;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus.Series;
import org.springframework.util.Assert;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

public class DefaultErrorViewResolver implements ErrorViewResolver, Ordered {
    private static final Map<Series, String> SERIES_VIEWS;
    private ApplicationContext applicationContext;
    private final Resources resources;
    private final TemplateAvailabilityProviders templateAvailabilityProviders;
    private int order;

    /** @deprecated */
    @Deprecated
    public DefaultErrorViewResolver(ApplicationContext applicationContext, ResourceProperties resourceProperties) {
        this(applicationContext, (Resources)resourceProperties);
    }

    public DefaultErrorViewResolver(ApplicationContext applicationContext, Resources resources) {
        this.order = 2147483647;
        Assert.notNull(applicationContext, "ApplicationContext must not be null");
        Assert.notNull(resources, "Resources must not be null");
        this.applicationContext = applicationContext;
        this.resources = resources;
        this.templateAvailabilityProviders = new TemplateAvailabilityProviders(applicationContext);
    }

    DefaultErrorViewResolver(ApplicationContext applicationContext, Resources resourceProperties, TemplateAvailabilityProviders templateAvailabilityProviders) {
        this.order = 2147483647;
        Assert.notNull(applicationContext, "ApplicationContext must not be null");
        Assert.notNull(resourceProperties, "Resources must not be null");
        this.applicationContext = applicationContext;
        this.resources = resourceProperties;
        this.templateAvailabilityProviders = templateAvailabilityProviders;
    }

    public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model) {
        ModelAndView modelAndView = this.resolve(String.valueOf(status.value()), model);
        if (modelAndView == null && SERIES_VIEWS.containsKey(status.series())) {
            modelAndView = this.resolve((String)SERIES_VIEWS.get(status.series()), model);
        }

        return modelAndView;
    }

    private ModelAndView resolve(String viewName, Map<String, Object> model) {
        String errorViewName = "error/" + viewName;
        TemplateAvailabilityProvider provider = this.templateAvailabilityProviders.getProvider(errorViewName, this.applicationContext);
        return provider != null ? new ModelAndView(errorViewName, model) : this.resolveResource(errorViewName, model);
    }

    private ModelAndView resolveResource(String viewName, Map<String, Object> model) {
        String[] var3 = this.resources.getStaticLocations();
        int var4 = var3.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            String location = var3[var5];

            try {
                Resource resource = this.applicationContext.getResource(location);
                resource = resource.createRelative(viewName + ".html");
                if (resource.exists()) {
                    return new ModelAndView(new DefaultErrorViewResolver.HtmlResourceView(resource), model);
                }
            } catch (Exception var8) {
            }
        }

        return null;
    }

    public int getOrder() {
        return this.order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    static {
        Map<Series, String> views = new EnumMap(Series.class);
        views.put(Series.CLIENT_ERROR, "4xx");
        views.put(Series.SERVER_ERROR, "5xx");
        SERIES_VIEWS = Collections.unmodifiableMap(views);
    }

    private static class HtmlResourceView implements View {
        private Resource resource;

        HtmlResourceView(Resource resource) {
            this.resource = resource;
        }

        public String getContentType() {
            return "text/html";
        }

        public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
            response.setContentType(this.getContentType());
            FileCopyUtils.copy(this.resource.getInputStream(), response.getOutputStream());
        }
    }
}
