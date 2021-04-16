//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.springframework.boot.autoconfigure.web;

import org.springframework.beans.factory.annotation.Value;

public class ErrorProperties {
    @Value("${error.path:/error}")
    private String path = "/error";
    private boolean includeException;
    private ErrorProperties.IncludeStacktrace includeStacktrace;
    private ErrorProperties.IncludeAttribute includeMessage;
    private ErrorProperties.IncludeAttribute includeBindingErrors;
    private final ErrorProperties.Whitelabel whitelabel;

    public ErrorProperties() {
        this.includeStacktrace = ErrorProperties.IncludeStacktrace.NEVER;
        this.includeMessage = ErrorProperties.IncludeAttribute.NEVER;
        this.includeBindingErrors = ErrorProperties.IncludeAttribute.NEVER;
        this.whitelabel = new ErrorProperties.Whitelabel();
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isIncludeException() {
        return this.includeException;
    }

    public void setIncludeException(boolean includeException) {
        this.includeException = includeException;
    }

    public ErrorProperties.IncludeStacktrace getIncludeStacktrace() {
        return this.includeStacktrace;
    }

    public void setIncludeStacktrace(ErrorProperties.IncludeStacktrace includeStacktrace) {
        this.includeStacktrace = includeStacktrace;
    }

    public ErrorProperties.IncludeAttribute getIncludeMessage() {
        return this.includeMessage;
    }

    public void setIncludeMessage(ErrorProperties.IncludeAttribute includeMessage) {
        this.includeMessage = includeMessage;
    }

    public ErrorProperties.IncludeAttribute getIncludeBindingErrors() {
        return this.includeBindingErrors;
    }

    public void setIncludeBindingErrors(ErrorProperties.IncludeAttribute includeBindingErrors) {
        this.includeBindingErrors = includeBindingErrors;
    }

    public ErrorProperties.Whitelabel getWhitelabel() {
        return this.whitelabel;
    }

    public static class Whitelabel {
        private boolean enabled = true;

        public Whitelabel() {
        }

        public boolean isEnabled() {
            return this.enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }

    public static enum IncludeAttribute {
        NEVER,
        ALWAYS,
        ON_PARAM;

        private IncludeAttribute() {
        }
    }

    public static enum IncludeStacktrace {
        NEVER,
        ALWAYS,
        ON_PARAM,
        /** @deprecated */
        @Deprecated
        ON_TRACE_PARAM;

        private IncludeStacktrace() {
        }
    }
}
