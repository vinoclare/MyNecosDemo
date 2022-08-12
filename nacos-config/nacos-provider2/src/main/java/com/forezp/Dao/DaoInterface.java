package com.forezp.Dao;

import com.forezp.bean.Bean;

import java.util.List;

public interface DaoInterface {
    List<Bean> findall();
    Bean findByName(String name);
    boolean addUser(Bean user);
    boolean updateByName(String name, Integer age);
    boolean deleteByName(String name);
}
