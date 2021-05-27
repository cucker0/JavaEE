//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.springframework.boot.autoconfigure.jdbc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.sql.DataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.jdbc.DataSourceInitializationMode;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.config.SortedResourcesFactoryBean;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.util.StringUtils;

class DataSourceInitializer {
    private static final Log logger = LogFactory.getLog(DataSourceInitializer.class);
    private final DataSource dataSource;
    private final DataSourceProperties properties;
    private final ResourceLoader resourceLoader;

    DataSourceInitializer(DataSource dataSource, DataSourceProperties properties, ResourceLoader resourceLoader) {
        this.dataSource = dataSource;
        this.properties = properties;
        this.resourceLoader = (ResourceLoader)(resourceLoader != null ? resourceLoader : new DefaultResourceLoader((ClassLoader)null));
    }

    DataSourceInitializer(DataSource dataSource, DataSourceProperties properties) {
        this(dataSource, properties, (ResourceLoader)null);
    }

    DataSource getDataSource() {
        return this.dataSource;
    }

    // 创建表架构
    boolean createSchema() {
        List<Resource> scripts = this.getScripts("spring.datasource.schema", this.properties.getSchema(), "schema");
        if (!scripts.isEmpty()) {
            if (!this.isEnabled()) {
                logger.debug("Initialization disabled (not running DDL scripts)");
                return false;
            }

            String username = this.properties.getSchemaUsername();
            String password = this.properties.getSchemaPassword();
            this.runScripts(scripts, username, password);
        }

        return !scripts.isEmpty();
    }

    // 向已经创建的架构表中初始化数据，一般是插入数据
    void initSchema() {
        List<Resource> scripts = this.getScripts("spring.datasource.data", this.properties.getData(), "data");
        if (!scripts.isEmpty()) {
            if (!this.isEnabled()) {
                logger.debug("Initialization disabled (not running data scripts)");
                return;
            }

            String username = this.properties.getDataUsername();
            String password = this.properties.getDataPassword();
            this.runScripts(scripts, username, password);
        }

    }

    private boolean isEnabled() {
        DataSourceInitializationMode mode = this.properties.getInitializationMode();
        if (mode == DataSourceInitializationMode.NEVER) {
            return false;
        } else {
            return mode != DataSourceInitializationMode.EMBEDDED || this.isEmbedded();
        }
    }

    private boolean isEmbedded() {
        try {
            return EmbeddedDatabaseConnection.isEmbedded(this.dataSource);
        } catch (Exception var2) {
            logger.debug("Could not determine if datasource is embedded", var2);
            return false;
        }
    }

    private List<Resource> getScripts(String propertyName, List<String> resources, String fallback) {
        if (resources != null) {
            return this.getResources(propertyName, resources, true);
        } else {
            String platform = this.properties.getPlatform();
            List<String> fallbackResources = new ArrayList();
            fallbackResources.add("classpath*:" + fallback + "-" + platform + ".sql");
            fallbackResources.add("classpath*:" + fallback + ".sql");
            return this.getResources(propertyName, fallbackResources, false);
        }
    }

    private List<Resource> getResources(String propertyName, List<String> locations, boolean validate) {
        List<Resource> resources = new ArrayList();
        Iterator var5 = locations.iterator();

        while(var5.hasNext()) {
            String location = (String)var5.next();
            Resource[] var7 = this.doGetResources(location);
            int var8 = var7.length;

            for(int var9 = 0; var9 < var8; ++var9) {
                Resource resource = var7[var9];
                if (resource.exists()) {
                    resources.add(resource);
                } else if (validate) {
                    throw new InvalidConfigurationPropertyValueException(propertyName, resource, "No resources were found at location '" + location + "'.");
                }
            }
        }

        return resources;
    }

    private Resource[] doGetResources(String location) {
        try {
            SortedResourcesFactoryBean factory = new SortedResourcesFactoryBean(this.resourceLoader, Collections.singletonList(location));
            factory.afterPropertiesSet();
            return (Resource[])factory.getObject();
        } catch (Exception var3) {
            throw new IllegalStateException("Unable to load resources from " + location, var3);
        }
    }

    private void runScripts(List<Resource> resources, String username, String password) {
        if (!resources.isEmpty()) {
            ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
            populator.setContinueOnError(this.properties.isContinueOnError());
            populator.setSeparator(this.properties.getSeparator());
            if (this.properties.getSqlScriptEncoding() != null) {
                populator.setSqlScriptEncoding(this.properties.getSqlScriptEncoding().name());
            }

            Iterator var5 = resources.iterator();

            while(var5.hasNext()) {
                Resource resource = (Resource)var5.next();
                populator.addScript(resource);
            }

            DataSource dataSource = this.dataSource;
            if (StringUtils.hasText(username) && StringUtils.hasText(password)) {
                dataSource = DataSourceBuilder.create(this.properties.getClassLoader()).driverClassName(this.properties.determineDriverClassName()).url(this.properties.determineUrl()).username(username).password(password).build();
            }

            DatabasePopulatorUtils.execute(populator, dataSource);
        }
    }
}
