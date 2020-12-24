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

-- 
SELECT * FROM tbl_employee;
SELECT * FROM tbl_emp;
