package com.java.springbootdruid2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

@Controller
public class DepartmentController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @ResponseBody
    @GetMapping("/deps")
    public List<Map<String, Object>> list() {
        String sql = "SELECT id, department_name FROM department LIMIT 0, 1000;";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

    @ResponseBody
    @GetMapping("/dep/{id}")
    public Map<String, Object> getDepartmentById(@PathVariable(value = "id") Integer id) {
        String sql = "SELECT id, department_name FROM department WHERE id=" + id;
        return jdbcTemplate.queryForMap(sql);
    }

    @GetMapping("/dep/add")
    public String add(@RequestParam("dep_name") String dep_name) {
        String sql = String.format("INSERT INTO department (department_name) VALUES ('%s')", dep_name);
        jdbcTemplate.update(sql);
        return "redirect:/deps";
    }
}
