-- 创建员工表
CREATE TABLE t_emp (
    id NUMBER PRIMARY KEY,
    last_name VARCHAR(32),
    gender VARCHAR(1),
    email VARCHAR(32)
);

INSERT INTO t_emp(id, last_name, gender, email) VALUES
(, '丘维声', 1, qiuweisheng@tsinghua.com);


-- 创建t_emp表的自增id计数器
CREATE SEQUENCE t_emp_id INCREMENT BY 1 START WITH 1;

-- 查询t_emp表的自增id计数器
SELECT t_emp_id.NEXTVAL FROM dual;

-- 批量插入数据
INSERT INTO t_emp(id, last_name, gender, email) VALUES(t_emp_id.NEXTVAL, '丘维声', '1', 'quiweisheng@tsinghua.com');
INSERT INTO t_emp(id, last_name, gender, email) VALUES(t_emp_id.NEXTVAL, '郭德刚', '1', 'quodg@qq.com');
