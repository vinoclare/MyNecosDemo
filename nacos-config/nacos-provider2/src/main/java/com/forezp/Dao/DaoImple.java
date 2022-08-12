package com.forezp.Dao;

import com.forezp.bean.Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("DaoImple")  //@Service为给类增加别名
public class DaoImple implements DaoInterface{

    @Autowired  //是用在JavaBean中的注解，通过byType形式，用来给指定的字段或方法注入所需的外部资源。
    private JdbcTemplate jdbcTemplate;  // jdbc连接工具类

    // 查询所有数据
    @Override
    public List<Bean> findall() {
        String sql = "select * from nacos1";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Bean>(Bean.class));
    }

    // 查询指定name
    @Override
    public Bean findByName(String name) {
        String sql = "select * from nacos1 where name=?";
        Object[] params = new Object[]{name};

        return jdbcTemplate.queryForObject(
                sql,
                params,
                new BeanPropertyRowMapper<>(Bean.class)
        );
    }

    // 增
    @Override
    public boolean addUser(Bean user) {
        String sql = "insert into nacos1(id, name, age)values(?,?,?)";
        Object[] params = {user.getId(), user.getName(), user.getAge()};
        return jdbcTemplate.update(sql, params) > 0;
    }

    // 改
    @Override
    public boolean updateByName(String name, Integer age) {
        String sql = "update nacos1 set age=? where name=?";
        Object[] params = {age, name};
        return jdbcTemplate.update(sql,params)>0;
    }

    // 删
    @Override
    public boolean deleteByName(String name) {
        String sql = "delete from nacos1 where name=?";
        Object[] params = {name};
        return jdbcTemplate.update(sql, params) > 0;
    }
}
