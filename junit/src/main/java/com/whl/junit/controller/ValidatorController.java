package com.whl.junit.controller;

import com.whl.junit.feignClient.TestServiceFeignClientInterface;
import com.whl.junit.molde.Person;
import com.whl.junit.molde.Result;
import com.whl.junit.unit.MiscUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
public class ValidatorController {

    @Resource
    TestServiceFeignClientInterface testServiceFeignClientInterface;

    @PostMapping("/save")
    @ResponseBody
    public ResponseEntity<Result> save(@Valid  @RequestBody Person person, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            Result res1 = MiscUtil.getValidateError(bindingResult);
            return new ResponseEntity<Result>(res1, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        Result res = new Result(200, "ok");
        return ResponseEntity.ok(res);
    }

    @GetMapping("getValues/{key}")
    @ResponseBody
    public Object getValues(@PathVariable String key) {
        return testServiceFeignClientInterface.getValues(key);
    }
}
