package com.whl.mongodb.mapper;

import com.whl.mongodb.model.Goods;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public interface GoodsMapper extends MongoRepository<Goods, Long> {

}
