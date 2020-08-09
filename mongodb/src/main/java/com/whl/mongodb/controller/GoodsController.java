package com.whl.mongodb.controller;

import com.whl.mongodb.model.BaseQuery;
import com.whl.mongodb.model.Goods;
import com.whl.mongodb.model.QueryResponseResult;
import com.whl.mongodb.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("GoodsController")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @PostMapping("/saveGoods")
    @ResponseBody
    public int saveGoods(@RequestBody Goods goods){
       return goodsService.save(goods);
    }

    @PostMapping("/getGoods")
    @ResponseBody
    public Goods getGoods(@RequestBody Goods goods){
        return goodsService.get(goods.getId());
    }

    @PostMapping("/getList")
    @ResponseBody
    public QueryResponseResult<Goods> getList(@RequestBody BaseQuery baseQuery){
        return goodsService.getList(baseQuery);
    }

    @PostMapping("/delete")
    @ResponseBody
    public int delete(@RequestBody Goods goods){
        return goodsService.delete(goods.getId());
    }

    @GetMapping("/getAll")
    @ResponseBody
    public List<Goods> getAll(){
        return goodsService.getAll();
    }
}
