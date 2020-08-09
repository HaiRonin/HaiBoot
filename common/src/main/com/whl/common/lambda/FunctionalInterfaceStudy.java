package com.whl.common.lambda;

import com.whl.common.lambda.functional.Converter;

public class FunctionalInterfaceStudy {

    public static void main(String[] args) {
        Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
        Integer converted = converter.convert("123");
        System.out.println(converted);    // 123

        Converter<String,Integer> converter1 = Integer::valueOf;
        Integer result = converter1.convert("1233");
        System.out.println(result);    // 123

        Converter<String,String> converter2 = String::toUpperCase;
        String reult2 = converter2.convert("avc");
        System.out.println(reult2);    // 123

    }
}
