package com.forezp.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient("nacos-provider")
public interface ProviderClient {
    @GetMapping("/hi")
    String hi(@RequestParam(value = "name", defaultValue = "User", required = false) String name);
}


