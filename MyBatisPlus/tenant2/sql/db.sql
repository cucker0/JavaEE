-- 创建数据库
CREATE DATABASE mp CHARSET 'utf8mb4';

USE mp;

-- 多租户 --start
DROP TABLE IF EXISTS sys_tenant;

CREATE TABLE sys_tenant (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(36),
    pwd VARCHAR(36) COMMENT '登录密码',
    phone VARCHAR(36) COMMENT '电话号码'
);

DROP TABLE IF EXISTS orders;
CREATE TABLE orders (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    expire_date DATETIME COMMENT '协议过期时间',
    amount DECIMAL(11, 2) COMMENT '金额',
    tenant_id BIGINT COMMENT '租户ID'
);

INSERT INTO sys_tenant(username, pwd, phone) VALUES
('tom', 'pptom', '13826180248'),
('charry', 'ppch', '13112345678'),
('marry', 'ppma', '13991005623');

INSERT INTO orders(expire_date, amount, tenant_id) VALUES
('2021-01-01', 8900.10, 1),
('2021-10-01', 992000.00, 1),
('2022-12-31', 1000000.00, 2);

-- 多租户 --end