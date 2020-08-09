package com.whl.elasticsearch.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    private Integer code;
    private String message;
    private Object data;


    public static Response successMsg(){
        Response response=new Response();
        response.setCode(200);
        response.setMessage("请求成功");
        return response;
    }

    public static Response failed404(){
        Response response=new Response();
        response.setCode(404);
        response.setMessage("请求失败");
        return response;
    }
}
