package com.whl.redis.controller;

import com.whl.redis.model.Test;
import com.whl.redis.utils.RedisUtil;
import com.whl.redis.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller("test")
public class TestController {

    private static int ExpireTime = 60;   // redis中存储的过期时间60s

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/setValue/{key}/{values}")
    @ResponseBody
    public String setValue(@PathVariable String key,@PathVariable String values){
        redisUtils.set(key,values);
        return "ok";
    }

    @PostMapping("/saveTest")
    @ResponseBody
    public int saveTest(@RequestBody Test test){
        redisUtils.set("TestController.id","111");
        return 1;
    }

    @GetMapping("/getValue")
    @ResponseBody
    public String getValue(){
       Object obj = redisUtils.get("TestController.id");
        return obj ==null?null:obj.toString();
    }

    @PostMapping("set")
    @ResponseBody
    public boolean redisset(@RequestBody Test test){
        test.setAge(String.valueOf(20));
        test.setCreateTime(new Date());

        return redisUtil.set("test.test",test,ExpireTime);

//        return redisUtil.set(key,value);
    }

    @GetMapping("getValues/{key}")
    @ResponseBody
    public Object getValues(@PathVariable String key) {
        return redisUtil.get(key).toString();
    }
}
