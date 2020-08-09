package com.whl.sharding.controller;

import com.whl.sharding.entity.Goods;
import com.whl.sharding.service.GoodsService;
import com.whl.sharding.util.SnowFlakeUtils;
import com.whl.sharding.util.SnowflakeIdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 保存方法使用了插入40条数据，根据我们的规则，会每个库插入20条，
 *
 *
 * @author wuhailang
 * @date 2020/04/29
 * 具体可参考：https://www.jianshu.com/p/92af4d298fea
 */
@RestController()
@RequestMapping("/sharding")
public class ShardingController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/save")
    @ResponseBody
    public String save(){
        SnowFlakeUtils snowFlake = new SnowFlakeUtils(2, 3);
        for(int i= 1 ; i <= 40 ; i ++){
            Goods goods = new Goods();
            goods.setGoodsId(snowFlake.nextId());
            goods.setGoodsName( "shangpin" + i);
            goods.setGoodsType((long) (i+1));
            goodsService.save(goods);
        }
        return "success";
    }
    @GetMapping("/getAll")
    @ResponseBody
    public List<Goods> getAll(){
        return goodsService.getAll();
    }

    @PostMapping("/detail")
    @ResponseBody
    public Goods detail(@RequestBody Goods goods){
        return goodsService.get(goods.getGoodsId());
    }

    @PostMapping("/delete")
    @ResponseBody
    public int delete(@RequestBody Goods goods){
        return goodsService.delete(goods.getGoodsId());
    }

    @PostMapping("/deleteAll")
    @ResponseBody
    public int deleteAll(){
        return goodsService.deleteAll();
    }
}