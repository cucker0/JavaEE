-- 创建数据库
CREATE DATABASE mybatis CHARSET 'utf8';

USE mybatis;

-- 创建Employee表
CREATE TABLE t_employee (
    id INT PRIMARY KEY AUTO_INCREMENT,
    last_name VARCHAR(32) NOT NULL,
    gender VARCHAR(1) DEFAULT '0' COMMENT '0: female, 1: male',
    email VARCHAR(32)
);

INSERT INTO t_employee (last_name, gender, email) VALUES
('大山', '0', 'da3@gmail.com'),
('花姐', '0', 'huajie@gmail.com')
;


SELECT * FROM t_employee;