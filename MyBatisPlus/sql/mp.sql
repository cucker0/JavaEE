-- Mybatis Plus

-- 创建数据库
CREATE DATABASE mp CHARSET 'utf8mb4';

USE mp;

-- 创建表
CREATE TABLE tbl_employee (
    id INT PRIMARY KEY AUTO_INCREMENT,
    last_name VARCHAR(50),
    email VARCHAR(50),
    gender CHAR(1),
    age INT
);

-- 插入数据
INSERT INTO tbl_employee (last_name, email, gender, age) VALUES
('Jone', 'jone@baomidou.com', '1', 18),
('Jack', 'jack@baomidou.com', '0', 20),
('Tom', 'tbl_employee', '1', 28),
('Sandy', 'sandy@baomidou.com', '0', 21),
('Billie', 'billie@baomidou.com', '1', 24)
;

INSERT INTO tbl_employee (last_name, email, gender, age) VALUES
('Tom', 'tom@baomidou.com', '1', 16),
('Tom', 'tom@baomidou.com', '1', 18),
('Tom', 'tom@baomidou.com', '1', 22),
('Tom', 'tom@baomidou.com', '1', 44),
('Tom', 'tom@baomidou.com', '1', 56)
;

-- 创建表
CREATE TABLE tbl_emp (
    id INT PRIMARY KEY AUTO_INCREMENT,
    last_name VARCHAR(50),
    email VARCHAR(50),
    gender CHAR(1),
    age INT,
    `version` INT DEFAULT 1
);

INSERT INTO tbl_emp (last_name, email, gender, age) VALUES
('Jone', 'jone@baomidou.com', '1', 18),
('Jack', 'jack@baomidou.com', '0', 20),
('Tom', 'tbl_employee', '1', 28),
('Sandy', 'sandy@baomidou.com', '0', 21),
('Billie', 'billie@baomidou.com', '1', 24)
;

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

DROP TABLE IF EXISTS tbl_user;
CREATE TABLE tbl_user (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(36),
    gender TINYINT DEFAULT 0 COMMENT '0:female, 1: male',
    phone VARCHAR(24),
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除标记，1:逻辑以删除，0:逻辑未删除'
);

INSERT INTO tbl_user(username, gender, phone) VALUES
('licui', 1, '13409607796'),
('zhabo', 1, '18764885175'),
('dingdang', 1, '13553842450');

-- 
SELECT * FROM tbl_employee;
SELECT * FROM tbl_emp;

SELECT * FROM `user`;
SELECT * FROM user_addr;
SELECT COUNT(*) FROM USER;

SELECT * FROM sys_tenant;
SELECT * FROM orders;


SELECT * FROM tbl_user;
