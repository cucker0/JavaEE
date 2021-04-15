//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.springframework.boot.web.servlet.error;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.error.ErrorAttributeOptions.Include;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@Order(-2147483648)
public class DefaultErrorAttributes implements ErrorAttributes, HandlerExceptionResolver, Ordered {
    private static final String ERROR_ATTRIBUTE = DefaultErrorAttributes.class.getName() + ".ERROR";
    private final Boolean includeException;

    public DefaultErrorAttributes() {
        this.includeException = null;
    }

    /** @deprecated */
    @Deprecated
    public DefaultErrorAttributes(boolean includeException) {
        this.includeException = includeException;
    }

    public int getOrder() {
        return -2147483648;
    }

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        this.storeErrorAttributes(request, ex);
        return null;
    }

    private void storeErrorAttributes(HttpServletRequest request, Exception ex) {
        request.setAttribute(ERROR_ATTRIBUTE, ex);
    }

    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = this.getErrorAttributes(webRequest, options.isIncluded(Include.STACK_TRACE));
        if (Boolean.TRUE.equals(this.includeException)) {
            options = options.including(new Include[]{Include.EXCEPTION});
        }

        if (!options.isIncluded(Include.EXCEPTION)) {
            errorAttributes.remove("exception");
        }

        if (!options.isIncluded(Include.STACK_TRACE)) {
            errorAttributes.remove("trace");
        }

        if (!options.isIncluded(Include.MESSAGE) && errorAttributes.get("message") != null) {
            errorAttributes.put("message", "");
        }

        if (!options.isIncluded(Include.BINDING_ERRORS)) {
            errorAttributes.remove("errors");
        }

        return errorAttributes;
    }

    /** @deprecated */
    @Deprecated
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> errorAttributes = new LinkedHashMap();
        errorAttributes.put("timestamp", new Date());
        this.addStatus(errorAttributes, webRequest);
        this.addErrorDetails(errorAttributes, webRequest, includeStackTrace);
        this.addPath(errorAttributes, webRequest);
        return errorAttributes;
    }

    private void addStatus(Map<String, Object> errorAttributes, RequestAttributes requestAttributes) {
        Integer status = (Integer)this.getAttribute(requestAttributes, "javax.servlet.error.status_code");
        if (status == null) {
            errorAttributes.put("status", 999);
            errorAttributes.put("error", "None");
        } else {
            errorAttributes.put("status", status);

            try {
                errorAttributes.put("error", HttpStatus.valueOf(status).getReasonPhrase());
            } catch (Exception var5) {
                errorAttributes.put("error", "Http Status " + status);
            }

        }
    }

    private void addErrorDetails(Map<String, Object> errorAttributes, WebRequest webRequest, boolean includeStackTrace) {
        Throwable error = this.getError(webRequest);
        if (error != null) {
            while(true) {
                if (!(error instanceof ServletException) || error.getCause() == null) {
                    errorAttributes.put("exception", error.getClass().getName());
                    if (includeStackTrace) {
                        this.addStackTrace(errorAttributes, error);
                    }
                    break;
                }

                error = error.getCause();
            }
        }

        this.addErrorMessage(errorAttributes, webRequest, error);
    }

    private void addErrorMessage(Map<String, Object> errorAttributes, WebRequest webRequest, Throwable error) {
        BindingResult result = this.extractBindingResult(error);
        if (result == null) {
            this.addExceptionErrorMessage(errorAttributes, webRequest, error);
        } else {
            this.addBindingResultErrorMessage(errorAttributes, result);
        }

    }

    private void addExceptionErrorMessage(Map<String, Object> errorAttributes, WebRequest webRequest, Throwable error) {
        errorAttributes.put("message", this.getMessage(webRequest, error));
    }

    protected String getMessage(WebRequest webRequest, Throwable error) {
        Object message = this.getAttribute(webRequest, "javax.servlet.error.message");
        if (!ObjectUtils.isEmpty(message)) {
            return message.toString();
        } else {
            return error != null && StringUtils.hasLength(error.getMessage()) ? error.getMessage() : "No message available";
        }
    }

    private void addBindingResultErrorMessage(Map<String, Object> errorAttributes, BindingResult result) {
        errorAttributes.put("message", "Validation failed for object='" + result.getObjectName() + "'. Error count: " + result.getErrorCount());
        errorAttributes.put("errors", result.getAllErrors());
    }

    private BindingResult extractBindingResult(Throwable error) {
        if (error instanceof BindingResult) {
            return (BindingResult)error;
        } else {
            return error instanceof MethodArgumentNotValidException ? ((MethodArgumentNotValidException)error).getBindingResult() : null;
        }
    }

    private void addStackTrace(Map<String, Object> errorAttributes, Throwable error) {
        StringWriter stackTrace = new StringWriter();
        error.printStackTrace(new PrintWriter(stackTrace));
        stackTrace.flush();
        errorAttributes.put("trace", stackTrace.toString());
    }

    private void addPath(Map<String, Object> errorAttributes, RequestAttributes requestAttributes) {
        String path = (String)this.getAttribute(requestAttributes, "javax.servlet.error.request_uri");
        if (path != null) {
            errorAttributes.put("path", path);
        }

    }

    public Throwable getError(WebRequest webRequest) {
        Throwable exception = (Throwable)this.getAttribute(webRequest, ERROR_ATTRIBUTE);
        return exception != null ? exception : (Throwable)this.getAttribute(webRequest, "javax.servlet.error.exception");
    }

    private <T> T getAttribute(RequestAttributes requestAttributes, String name) {
        return requestAttributes.getAttribute(name, 0);
    }
}
