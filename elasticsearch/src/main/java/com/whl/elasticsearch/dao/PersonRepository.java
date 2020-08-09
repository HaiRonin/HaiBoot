package com.whl.elasticsearch.dao;

import com.whl.elasticsearch.bean.Person;

public interface  PersonRepository extends ElasticsearchRepository<Person,String> {


}