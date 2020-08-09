package com.whl.mongodb.base;

import com.whl.mongodb.model.BaseQuery;
import com.whl.mongodb.model.QueryResponseResult;

import java.util.List;

public interface BaseService<T, ID>  {

   abstract int save(T t);

    abstract int delete(Long id);

    abstract T get(Long id);

    abstract QueryResponseResult<T> getList(BaseQuery baseQuery);

    abstract List<T> getAll();

}
