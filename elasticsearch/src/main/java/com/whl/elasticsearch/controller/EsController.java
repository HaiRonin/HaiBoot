package com.whl.elasticsearch.controller;

import com.whl.elasticsearch.bean.Person;
import com.whl.elasticsearch.bean.Request;
import com.whl.elasticsearch.bean.Response;
import com.whl.elasticsearch.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/es")
public class EsController {


    @Autowired
    PersonService personService;


    /**
     * 向es添加数据
     */
    @PostMapping("/insert")
    @ResponseBody
    public String insert(@RequestBody Person person){
        Person savePerson = null;
        try {
            savePerson=  personService.save(person);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return savePerson.toString();
    }


    /**
     * 从es删除数据
     * @param person
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public Object delete(@RequestBody Person person){

        try {
            personService.delete(person);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.failed404();
        }
        return Response.successMsg();

    }


    /**
     * 查询操作
     * @return
     */
    @PostMapping("/query")
    @ResponseBody
    public Object query(@RequestBody Request request){

        Response response=new Response();
        try {
            Page<Person> personPage= personService.pageQuery(request.getPageNo(),request.getPageSize(),request.getKey());
            response.setData(personPage);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.failed404();
        }
        return response;

    }
}

