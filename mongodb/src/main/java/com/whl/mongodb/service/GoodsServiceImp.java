package com.whl.mongodb.service;

import com.whl.mongodb.mapper.GoodsMapper;
import com.whl.mongodb.model.BaseQuery;
import com.whl.mongodb.model.Goods;
import com.whl.mongodb.model.QueryResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class GoodsServiceImp implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;


    @Override
    public int save(Goods goods) {
        if(goodsMapper.existsById(goods.getId())){
            goodsMapper.save(goods);
        }
        else{
            goodsMapper.insert(goods);
        }
        return 0;
    }

    @Override
    public int delete(Long id) {
        goodsMapper.deleteById(id);
        return 1;
    }

    @Override
    public Goods get(Long id) {
        Goods goods = new Goods();
        goods.setId(id);
        Example<Goods> example = Example.of(goods);
        Optional<Goods>  OptionalGoods =goodsMapper.findOne(example);
        if(null != OptionalGoods){
            return OptionalGoods.get();
        }
        return null;
    }

    @Override
    public QueryResponseResult<Goods> getList(BaseQuery baseQuery) {
        Goods goods = new Goods();
        if(!StringUtils.isEmpty(baseQuery.getKeywork())){
            goods.setUserName(baseQuery.getKeywork());
        }
        Example<Goods> example = Example.of(goods);

        Sort sort = Sort.sort(Goods.class).by(StringUtils.isEmpty(baseQuery.getSort())?"id":baseQuery.getSort());
        PageRequest page = PageRequest.of(baseQuery.getPageNum(),baseQuery.getPageSize(),sort);

        Page<Goods> goodsPage = goodsMapper.findAll(example,page);

        QueryResponseResult<Goods> goodsQueryResponseResult = new QueryResponseResult<>();
        goodsQueryResponseResult.setRows(goodsPage.getContent());
        goodsQueryResponseResult.setPageNum(baseQuery.getPageNum());
        goodsQueryResponseResult.setPageSize(baseQuery.getPageSize());
        goodsQueryResponseResult.setTotalPage(goodsPage.getTotalPages());
        goodsQueryResponseResult.setTotalRows(goodsPage.getTotalElements());
        return goodsQueryResponseResult;
    }

    @Override
    public List<Goods> getAll() {
        return goodsMapper.findAll();
    }


}
