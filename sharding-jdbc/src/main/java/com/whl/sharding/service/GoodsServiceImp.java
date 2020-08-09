package com.whl.sharding.service;

import com.whl.sharding.dao.GoodsMapper;
import com.whl.sharding.entity.Goods;
import com.whl.sharding.model.BaseQuery;
import com.whl.sharding.model.QueryResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImp implements GoodsService {

    @Autowired
    private GoodsMapper myGoodsMapper;

    @Override
    public int save(Goods goods) {
        return myGoodsMapper.insert(goods);
    }

    @Override
    public int delete(Long id) {
        return myGoodsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Goods get(Long id) {
        return myGoodsMapper.selectByPrimaryKey(id);
    }

    @Override
    public QueryResponseResult<Goods> getList(BaseQuery baseQuery) {
        return null;
    }

    @Override
    public List<Goods> getAll() {
        return myGoodsMapper.getAll();
    }


    @Override
    public int deleteAll() {
        return myGoodsMapper.deleteAll();
    }
}
