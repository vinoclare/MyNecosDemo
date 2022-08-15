package com.example.demo.Controller;

import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.util.AbstractCasFilter;
import org.jasig.cas.client.validation.Assertion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequestMapping("/casTest2")
@Controller

public class TestController {
    @Value(value = "${cas.server-url-prefix}")
    private String serverUrlPrefix = "";
    @Value(value = "${cas.client-host-url}")
    private String clientHostUrl = "";

    @GetMapping("/user2")
    @ResponseBody
    public String user(HttpServletRequest request) throws Exception {
        Assertion assertion = (Assertion) request.getSession().getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);
        String loginName = null;
        if (assertion != null) {
            AttributePrincipal principal = assertion.getPrincipal();
            loginName = principal.getName();
            System.out.println("访问者2:" + loginName);
        }
        SSL.ignoreSsl();
        return "访问者2:" + loginName;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:" + serverUrlPrefix + "/logout?service=" + clientHostUrl + "/casTest2/user2";
    }


    @GetMapping("/test2")
    public String test() {
        return "test2....";
    }
}