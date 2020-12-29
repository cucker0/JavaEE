package com.java.mp.config;

import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.NullValue;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class MyTenantLineHandler implements TenantLineHandler {
    @Autowired
    private ApiContext apiContext;
    // 多租户标识
    private static final String SYSTEM_TENANT_ID = "tenant_id";
    // 需要过滤的表
    private static final List<String> IGNORE_TENANT_TABLES = new ArrayList<>();

    static {
        IGNORE_TENANT_TABLES.add("sys_tenant");
    }

    @Override
    public Expression getTenantId() {
        // 模拟用户ID，正常情况可以从用户登录后，把用户基本信息保存到session，从session中获取用户人tenant_id
        Long tenantId = apiContext.getCurrentTenantId();
        if (tenantId == null) {
            return new NullValue();
        }
        return new LongValue(tenantId);
    }

    @Override
    public String getTenantIdColumn() {
        return SYSTEM_TENANT_ID;
    }

    @Override
    public boolean ignoreTable(String tableName) {
        return IGNORE_TENANT_TABLES.stream().anyMatch((e) -> e.equalsIgnoreCase(tableName));
        // return false;
    }
}
