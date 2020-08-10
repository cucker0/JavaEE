-- 创建数据库
CREATE DATABASE restful CHARSET 'utf8';

USE restful;
-- 创建部门表
CREATE TABLE t_department (
    id INT PRIMARY KEY AUTO_INCREMENT,
    department_name VARCHAR(32)
);


-- 创建员工表
CREATE TABLE t_employee (
    id INT PRIMARY KEY AUTO_INCREMENT,
    last_name VARCHAR(32) NOT NULL,
    gender TINYINT DEFAULT 0 COMMENT '0: female, 1: male',
    email VARCHAR(32),
    salary DOUBLE(11, 2) DEFAULT 0.0,
    birth DATETIME,
    department_id INT NOT NULL
);