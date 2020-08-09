package com.whl.nacos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/get")
    public String get(){
        return "SpringCloud 33333 alibaba 我来啦...";
    }
}