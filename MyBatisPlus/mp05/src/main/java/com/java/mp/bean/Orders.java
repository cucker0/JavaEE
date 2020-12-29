package com.java.mp.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.sql.Date;

@TableName("orders")
public class Orders {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Date expireDate;
    private BigDecimal amount;
    private Long tenantId;

    public Orders() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", expireDate=" + expireDate +
                ", amount=" + amount +
                ", tenantId=" + tenantId +
                '}';
    }
}
