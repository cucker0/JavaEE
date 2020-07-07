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
INSERT INTO t_department (id, NAME) VALUES
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

