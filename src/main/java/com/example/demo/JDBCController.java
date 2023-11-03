package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.List;
import java.lang.*;
import java.util.Scanner;


@RestController
public class JDBCController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    //查询数据库的所有信息
    //没有实体类，数据库里的东西，怎么获取？ Map

    //查
    @GetMapping("/userList")
    public List<Map<String,Object> > userList(){
        String sql="select * from mybatis.user2";
        List<Map<String,Object>> list_maps=jdbcTemplate.queryForList(sql);
        return list_maps;
    }


    //增
    @GetMapping("/addUser")
    public String addUser(){
        String sql="insert into mybatis.user2(id,name,pwd,perms) values(3,'xiaoming','123456','NULL')";
        jdbcTemplate.update(sql);
        return "add-ok";
    }

    //改
    @GetMapping("updateUser/{id}/{name}/{pwd}")
    public String updateUser(@PathVariable("id") int id,@PathVariable("name") String name,@PathVariable("pwd") String pwd){
        String sql="update mybatis.user2 set  name=?,pwd=?  where  id="+id;

        //封装Object
        Object[] objects = new Object[2];
        objects[0]=name;
        objects[1]=pwd;

        jdbcTemplate.update(sql,objects);
        return "update-ok";
    }

    //删
    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") int id){
        String sql="delete from mybatis.user2 where id=?";
        jdbcTemplate.update(sql,id);
        return "delete-ok";
    }
}