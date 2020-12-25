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
DROP TABLE IF EXISTS USER;

CREATE TABLE `user`
(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
	tenant_id BIGINT(20) NOT NULL COMMENT '租户ID',
	`name` VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名'
);

DROP TABLE IF EXISTS user_addr;

CREATE TABLE `user_addr`
(
  id BIGINT(20) PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
  user_id BIGINT(20) NOT NULL COMMENT 'user.id',
  address VARCHAR(128) NULL DEFAULT NULL COMMENT '地址名称'
);

INSERT INTO `user` (tenant_id, `name`) VALUES
(1, 'Jone'),
(1, 'Jack'),
(1, 'Tom'),
(0, 'Sandy'),
(0, 'Billie');

INSERT INTO user_addr (user_id, address) VALUES
(1, '中国浙江省杭州市余杭区文一西路969号 (311121)'),
(1, '中国香港铜锣湾勿地臣街1号时代广场1座26楼'),
(0, '深圳市南山区海天二路33号腾讯滨海大厦'),
(0, 'Suite 02 & 03, Level 17, Centrepoint South Tower, Mid Valley City, Lingkaran Syed Putra, 59200 Kuala Lumpur, Malaysia');

-- 多租户 --end

-- 
SELECT * FROM tbl_employee;
SELECT * FROM tbl_emp;

SELECT * FROM `user`;
SELECT * FROM user_addr;