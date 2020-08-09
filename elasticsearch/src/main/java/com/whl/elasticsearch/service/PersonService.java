package com.whl.elasticsearch.service;

import com.whl.elasticsearch.bean.Person;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PersonService {

    long count();

    Person save(Person person);

    void delete(Person person);

    Iterable<Person> getAll();

    List<Person> getByName(String name);

    Page<Person> pageQuery(Integer pageNo, Integer pageSize, String kw);
}

