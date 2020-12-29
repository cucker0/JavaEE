package com.java.mp.tenant.config;

import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.NullValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

// @Slf4j
@Component
public class MyTenantHandler implements TenantHandler {
    // 多租户标识
    private static final String SYSTEM_TENANT_ID = "tenant_id";

    // 需要过滤的表
    private static final List<String> IGNORE_TENANT_TABLES = new ArrayList<>();

    @Autowired
    private ApiContext apiContext;

    /**
     * 租户ID
     * @return
     */
    @Override
    public Expression getTenantId(boolean select) {
        // 从当前系统上下文中取出当前请求的服务商ID，通过解析器注入到SQL中。正常业务可从session对象中获取租户的ID信息
        Long tenantId = apiContext.getCurrentTenantId();
        // log.debug("当前租户为{}", tenantId);
        if (tenantId == null) {
            return new NullValue();
        }
        return new LongValue(tenantId);
    }

    /**
     * 租户字段名
     * @return
     */
    @Override
    public String getTenantIdColumn() {
        return SYSTEM_TENANT_ID;
    }

    /**
     * 根据表名判断是否进行过滤
     * 忽略掉一些表：如租户表（sys_tenant）本身不需要执行这样的处理
     * @param tableName
     * @return
     */
    @Override
    public boolean doTableFilter(String tableName) {
        return IGNORE_TENANT_TABLES.stream().anyMatch((e) -> e.equalsIgnoreCase(tableName));
    }
}
