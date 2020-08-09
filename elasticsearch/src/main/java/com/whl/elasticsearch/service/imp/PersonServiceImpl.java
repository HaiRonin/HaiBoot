package com.whl.elasticsearch.service.imp;

import com.whl.elasticsearch.bean.Person;
import com.whl.elasticsearch.dao.PersonRepository;
import com.whl.elasticsearch.service.PersonService;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public long count() {
        return personRepository.count();
    }

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    public void delete(Person person) {
        personRepository.delete(person);
    }

    @Override
    public Iterable<Person> getAll() {
        return personRepository.findAll();
    }

    @Override
    public List<Person> getByName(String name) {
        List<Person> personList=new ArrayList<>();
        MatchQueryBuilder queryBuilder=new MatchQueryBuilder("name",name);
        Iterable<Person> personIterable=personRepository.search(queryBuilder);
        personIterable.forEach(personList::add);
        return personList;
    }

    @Override
    public Page<Person> pageQuery(Integer pageNo, Integer pageSize, String kw) {
        SearchQuery searchQuery=new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchPhraseQuery("name",kw)).withPageable(PageRequest.of(pageNo,pageSize)).build();
        return personRepository.search(searchQuery);
    }
}
