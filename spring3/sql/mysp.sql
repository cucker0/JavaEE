-- 创建数据库
CREATE DATABASE mysp CHARSET utf8;


USE mysp;
-- 创建表employee
CREATE TABLE employee (
    id INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(25),
    age INT NOT NULL DEFAULT 1,
    department_id INT COMMENT '部门ID'
) COMMENT='员工表';


-- 创建部门表
CREATE TABLE t_department (
    id INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(32)
) COMMENT='部门表';

-- 批量插入部门信息
INSERT INTO t_department (id, `name`) VALUES
(NULL, '行政部'),
(NULL, '人事部'),
(NULL, '财务部')
;

SELECT * FROM t_department;

-- 批量插入员工信息
INSERT INTO employee VALUES
(NULL, 'lilei', 18, 1),
(NULL, 'hanmeimei', 17, 1),
(NULL, 'charry', 18, 2)
;

SELECT * FROM employee;

-- update employee set age = ? where `name` = ?;

-- delete from employee where `name` in ('刘备', '张飞', '关羽');


SELECT id, `name`, age, department_id FROM employee WHERE id = 1;

SELECT e.id, e.name, age, department_id AS 'department.id', d.id, d.name FROM employee e
LEFT JOIN t_department d ON e.department_id = d.id
WHERE e.id = 1



-- 
-- 事务

-- 创建book表
CREATE TABLE t_book (
    id INT PRIMARY KEY AUTO_INCREMENT,
    book_name VARCHAR(32) NOT NULL,
    price DOUBLE(9, 2) DEFAULT 0.0
) COMMENT='图书表';

-- 创建图书库存表，主要是为了练习spring的事务
CREATE TABLE book_stock (
    book_id INT NOT NULL,
    stock INT NOT NULL DEFAULT 0
) COMMENT='图书库存表';

-- 用户表
CREATE TABLE t_account (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(32) NOT NULL,
    `password` VARCHAR(32),
    balance DOUBLE(11, 2) DEFAULT 0 NOT NULL
) COMMENT='用户表';


-- 批量插入图书
INSERT INTO t_book (book_name, price) VALUES
('黑猫侦探', 34.00),
('神奇树屋', 99.00),
('做个人格独立的人', 50.00)
;

-- 插入图书库存
INSERT INTO book_stock (book_id, stock) VALUES
(1, 2),
(2, 3),
(3, 2)
;

-- 批量插入用户
INSERT INTO t_account (username, `password`, balance) VALUES
('郁可唯', 'kk123', 300.00),
('蒋大为', 'jj123', 300.00),
('彭素', 'pp123', 300.00),
;
