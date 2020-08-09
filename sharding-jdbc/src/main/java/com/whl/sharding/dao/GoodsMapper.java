package com.whl.sharding.dao;


import com.whl.sharding.entity.Goods;

import java.util.List;


public interface GoodsMapper {
    int deleteByPrimaryKey(Long goodsId);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Long goodsId);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

    List<Goods> getAll();

    int deleteAll();
}