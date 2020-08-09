package com.whl.common.lambda.main;

import com.whl.common.lambda.model.Person;
import com.whl.common.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class PersonFactoryMain {
    public static void main(String[] args) {

//        List<String> stringCollection = new ArrayList<>();
//        stringCollection.add("ddd2");
//        stringCollection.add("aaa2");
//        stringCollection.add("bbb1");
//        stringCollection.add("aaa1");
//        stringCollection.add("bbb3");
//        stringCollection.add("ccc");
//        stringCollection.add("bbb2");
//        stringCollection.add("ddd1");
//
//
//        log.info(JsonUtils.objectToJson(stringCollection.stream().map(e->e.length()).collect(Collectors.toList())));
//

        List<Person> Persons = new ArrayList<>();
        Persons.add(new Person("aa","vv"));
        Persons.add(new Person("cc","dd"));
        Persons.add(new Person("ee","ff"));
        Persons.add(new Person("gg","hh"));
        Persons.stream().map(p->(p.getFirstName())).forEach(System.out::print);
    }
}
