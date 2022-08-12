package com.forezp.web;

import com.forezp.Dao.DaoImple;
import com.forezp.bean.Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MySQLController {

    @Autowired
    private DaoImple Dao;

    @Value("${id:88}")
    private Integer id;

    @Value("${name:XiaoHong}")
    private String name;

    @Value("${age:40}")
    private Integer age;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String findAll(){
        List<Bean> list = Dao.findall();
        for (Bean user:list){
            System.out.println(user.getName() + ", age is " + user.getAge());
        }
        return "查询所有数据(3)!";
    }

    @RequestMapping(value = "/this", method = RequestMethod.GET)
    public String findByName(String name){
        name = "Liu";
        Bean user;
        user = Dao.findByName(name);
        System.out.println(user.getName() + ", age is " + user.getAge());
        return "传参查询！";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Bean user){
        user.setName(name);
        user.setId(id);
        user.setAge(age);
        System.out.println(user.getName() + "接收参数。。。");
        boolean flag = Dao.addUser(user);
        System.out.println("....." + flag + "....");
        return "添加";
    }

    @RequestMapping(value = "/del", method = RequestMethod.GET)
    public String del(String name){
        name = "小明";
        boolean flag = Dao.deleteByName(name);
        System.out.println(flag);
        return "根据name删除";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(String name, Integer age){
        name = "李";
        age = 14;
        System.out.println(Dao.findByName(name).getName());
        System.out.println(Dao.updateByName(name,age));
        System.out.println(Dao.findByName(name).getAge());
        return "更新数据！";
    }
}
