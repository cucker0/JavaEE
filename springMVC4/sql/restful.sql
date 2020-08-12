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

-- 批量导入部门信息
INSERT INTO t_department (department_name) VALUES
('行政部'),
('财务部'),
('IT部'),
('销售部')
;

-- 批量导入员工信息
INSERT INTO t_employee (last_name, gender, email, salary, birth, department_id) VALUES
('李露', 0, 'lilu@qq.com', 5000.00, '1991-01-01', 4),
('杨帆', 1, 'yangf@163.com', 8000.00, '1992-10-01', 1),
('张价', 0, 'zhangj@gmail.com', 12000.00, '1989-12-12', 3),
('王维', 1, 'wangw@hotmail.com', 5000.00, '1990-06-01', 2)
;

-- 添加部门
INSERT INTO t_department (department_name) VALUES ('');

-- 删除指定id部门
DELETE FROM t_department WHERE id = ?;

-- 更新部门信息
UPDATE t_department SET department_name = ? WHERE id = ?;

-- 查询指定部门
SELECT id, department_name FROM t_department WHERE id = ?;

-- 查询所有部门
SELECT id, department_name FROM t_department;


-- 添加员工
INSERT INTO t_employee (last_name, gender, email, salary, birth, department_id) VALUES (?, ?, ?, ?, ?, ?);

-- 删除指定id的员工
DELETE FROM t_employee WHERE id = ?;

-- 更新员工信息
UPDATE t_employee SET last_name = ?, gender = ?, email = ?, salary = ?, birth = ?, department_id = ? WHERE id = ?;

-- 查询指定id员工
SELECT id, last_name, gender, email, salary, birth, department_id FROM t_employee WHERE id = ?;

-- 查询所有员工
SELECT id, last_name, gender, email, salary, birth, department_id FROM t_employee;