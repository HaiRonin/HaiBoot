package com.whl.sharding.service;


import com.whl.sharding.entity.Goods;

public interface GoodsService extends BaseService<Goods,Long> {

    int deleteAll();
}
