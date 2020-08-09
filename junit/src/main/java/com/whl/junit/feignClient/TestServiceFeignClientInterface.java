package com.whl.junit.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient( value = "REDIS" )
public interface TestServiceFeignClientInterface {
    @GetMapping("getValues/{key}")
    public Object getValues(@PathVariable String key);

}
