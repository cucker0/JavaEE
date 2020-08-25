package com.java.customConversion;

import com.java.curd.bean.Department;
import com.java.curd.bean.Employee;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 把字符串型 Employee转换成 Employee对象
 * lastName,gender,email,salary,birth,departmentId
 */
@Component
public class EmployeeConverter implements Converter<String, Employee> {

    @Override
    public Employee convert(String s) {
        if (s == null) {
            return null;
        }
        String[] args = s.split(",");
        if (args.length == 6) {
            String lastName = args[0];
            int gender = Integer.parseInt(args[1]);
            String email = args[2];
            double salary = Double.parseDouble(args[3]);
            LocalDate birth = LocalDate.parse(args[4], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Department department = new Department(Long.parseLong(args[5]), null);
            Employee employee = new Employee(null, lastName, gender, email, salary, birth, department);
            System.out.println("源字符串：" + s);
            System.out.println("转换后：" + employee);
            return employee;
        }
        return null;
    }
}
