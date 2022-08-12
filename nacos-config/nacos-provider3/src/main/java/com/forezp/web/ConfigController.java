package com.forezp.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RefreshScope
public class ConfigController {

    @Value("${username:Luck}")
    private String username;

    @Value("${x:20}")
    private Integer x;

    @Value("${test:No}")
    private String test;

    @RequestMapping("/username")
    public String get() {
        return username;
    }

    @RequestMapping("/x")
    public Integer get1() {return x;}

    @RequestMapping("/test")
    public String get2() {return test;}

}