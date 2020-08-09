package com.whl.common.lambda;

import lombok.extern.slf4j.Slf4j;

import com.whl.common.utils.JsonUtils;

import java.util.*;

/***
 * 只需要给静态方法 Collections.sort 传入一个List对象以及一个比较器来按指定顺序排列。通常做法都是创建一个匿名的比较器对象然后将其传递给sort方法。
 */
@Slf4j
public class CollectionsSortStudy {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        //旧的
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });
        log.info("name={}",JsonUtils.objectToJson(names));

        Collections.sort(names,(a,b) -> a.compareTo(b));
        log.info("name={}",JsonUtils.objectToJson(names));


    }
}
