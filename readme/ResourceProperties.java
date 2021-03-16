//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.springframework.boot.autoconfigure.web;

import java.time.Duration;
import org.springframework.boot.autoconfigure.web.WebProperties.Resources;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.DeprecatedConfigurationProperty;

/** @deprecated */
@Deprecated
@ConfigurationProperties(
        prefix = "spring.resources",
        ignoreUnknownFields = false
)
public class ResourceProperties extends Resources {
    private final ResourceProperties.Chain chain = new ResourceProperties.Chain();
    private final ResourceProperties.Cache cache = new ResourceProperties.Cache();

    public ResourceProperties() {
    }

    @DeprecatedConfigurationProperty(
            replacement = "spring.web.resources.static-locations"
    )
    public String[] getStaticLocations() {
        return super.getStaticLocations();
    }

    @DeprecatedConfigurationProperty(
            replacement = "spring.web.resources.add-mappings"
    )
    public boolean isAddMappings() {
        return super.isAddMappings();
    }

    public ResourceProperties.Chain getChain() {
        return this.chain;
    }

    public ResourceProperties.Cache getCache() {
        return this.cache;
    }

    /** @deprecated */
    @Deprecated
    public static class Cache extends org.springframework.boot.autoconfigure.web.WebProperties.Resources.Cache {
        private final ResourceProperties.Cache.Cachecontrol cachecontrol = new ResourceProperties.Cache.Cachecontrol();

        public Cache() {
        }

        @DeprecatedConfigurationProperty(
                replacement = "spring.web.resources.cache.period"
        )
        public Duration getPeriod() {
            return super.getPeriod();
        }

        public ResourceProperties.Cache.Cachecontrol getCachecontrol() {
            return this.cachecontrol;
        }

        @DeprecatedConfigurationProperty(
                replacement = "spring.web.resources.cache.use-last-modified"
        )
        public boolean isUseLastModified() {
            return super.isUseLastModified();
        }

        /** @deprecated */
        @Deprecated
        public static class Cachecontrol extends org.springframework.boot.autoconfigure.web.WebProperties.Resources.Cache.Cachecontrol {
            public Cachecontrol() {
            }

            @DeprecatedConfigurationProperty(
                    replacement = "spring.web.resources.cache.cachecontrol.max-age"
            )
            public Duration getMaxAge() {
                return super.getMaxAge();
            }

            @DeprecatedConfigurationProperty(
                    replacement = "spring.web.resources.cache.cachecontrol.no-cache"
            )
            public Boolean getNoCache() {
                return super.getNoCache();
            }

            @DeprecatedConfigurationProperty(
                    replacement = "spring.web.resources.cache.cachecontrol.no-store"
            )
            public Boolean getNoStore() {
                return super.getNoStore();
            }

            @DeprecatedConfigurationProperty(
                    replacement = "spring.web.resources.cache.cachecontrol.must-revalidate"
            )
            public Boolean getMustRevalidate() {
                return super.getMustRevalidate();
            }

            @DeprecatedConfigurationProperty(
                    replacement = "spring.web.resources.cache.cachecontrol.no-transform"
            )
            public Boolean getNoTransform() {
                return super.getNoTransform();
            }

            @DeprecatedConfigurationProperty(
                    replacement = "spring.web.resources.cache.cachecontrol.cache-public"
            )
            public Boolean getCachePublic() {
                return super.getCachePublic();
            }

            @DeprecatedConfigurationProperty(
                    replacement = "spring.web.resources.cache.cachecontrol.cache-private"
            )
            public Boolean getCachePrivate() {
                return super.getCachePrivate();
            }

            @DeprecatedConfigurationProperty(
                    replacement = "spring.web.resources.cache.cachecontrol.proxy-revalidate"
            )
            public Boolean getProxyRevalidate() {
                return super.getProxyRevalidate();
            }

            @DeprecatedConfigurationProperty(
                    replacement = "spring.web.resources.cache.cachecontrol.stale-while-revalidate"
            )
            public Duration getStaleWhileRevalidate() {
                return super.getStaleWhileRevalidate();
            }

            @DeprecatedConfigurationProperty(
                    replacement = "spring.web.resources.cache.cachecontrol.stale-if-error"
            )
            public Duration getStaleIfError() {
                return super.getStaleIfError();
            }

            @DeprecatedConfigurationProperty(
                    replacement = "spring.web.resources.cache.cachecontrol.s-max-age"
            )
            public Duration getSMaxAge() {
                return super.getSMaxAge();
            }
        }
    }

    /** @deprecated */
    @Deprecated
    public static class Fixed extends org.springframework.boot.autoconfigure.web.WebProperties.Resources.Chain.Strategy.Fixed {
        public Fixed() {
        }

        @DeprecatedConfigurationProperty(
                replacement = "spring.web.resources.chain.strategy.fixed.enabled"
        )
        public boolean isEnabled() {
            return super.isEnabled();
        }

        @DeprecatedConfigurationProperty(
                replacement = "spring.web.resources.chain.strategy.fixed.paths"
        )
        public String[] getPaths() {
            return super.getPaths();
        }

        @DeprecatedConfigurationProperty(
                replacement = "spring.web.resources.chain.strategy.fixed.version"
        )
        public String getVersion() {
            return super.getVersion();
        }
    }

    /** @deprecated */
    @Deprecated
    public static class Content extends org.springframework.boot.autoconfigure.web.WebProperties.Resources.Chain.Strategy.Content {
        public Content() {
        }

        @DeprecatedConfigurationProperty(
                replacement = "spring.web.resources.chain.strategy.content.enabled"
        )
        public boolean isEnabled() {
            return super.isEnabled();
        }

        @DeprecatedConfigurationProperty(
                replacement = "spring.web.resources.chain.strategy.content.paths"
        )
        public String[] getPaths() {
            return super.getPaths();
        }
    }

    /** @deprecated */
    @Deprecated
    public static class Strategy extends org.springframework.boot.autoconfigure.web.WebProperties.Resources.Chain.Strategy {
        private final ResourceProperties.Fixed fixed = new ResourceProperties.Fixed();
        private final ResourceProperties.Content content = new ResourceProperties.Content();

        public Strategy() {
        }

        public ResourceProperties.Fixed getFixed() {
            return this.fixed;
        }

        public ResourceProperties.Content getContent() {
            return this.content;
        }
    }

    /** @deprecated */
    @Deprecated
    public static class Chain extends org.springframework.boot.autoconfigure.web.WebProperties.Resources.Chain {
        private final ResourceProperties.Strategy strategy = new ResourceProperties.Strategy();
        private boolean htmlApplicationCache = false;

        public Chain() {
        }

        @DeprecatedConfigurationProperty(
                replacement = "spring.web.resources.chain.enabled"
        )
        public Boolean getEnabled() {
            return super.getEnabled();
        }

        @DeprecatedConfigurationProperty(
                replacement = "spring.web.resources.chain.cache"
        )
        public boolean isCache() {
            return super.isCache();
        }

        @DeprecatedConfigurationProperty(
                reason = "The appcache manifest feature is being removed from browsers."
        )
        public boolean isHtmlApplicationCache() {
            return this.htmlApplicationCache;
        }

        public void setHtmlApplicationCache(boolean htmlApplicationCache) {
            this.htmlApplicationCache = htmlApplicationCache;
            this.customized = true;
        }

        @DeprecatedConfigurationProperty(
                replacement = "spring.web.resources.chain.compressed"
        )
        public boolean isCompressed() {
            return super.isCompressed();
        }

        public ResourceProperties.Strategy getStrategy() {
            return this.strategy;
        }
    }
}
