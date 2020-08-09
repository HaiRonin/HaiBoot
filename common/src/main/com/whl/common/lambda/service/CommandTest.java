package main.com.whl.common.lambda.service;

import org.springframework.cglib.core.CollectionUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.springframework.cglib.core.CollectionUtils.filter;


public class CommandTest {
    public static void main(String[] args) {
//        ProcessArray pa = new ProcessArray();
//        int[] target = {3, -4, 6, 4};
//        pa.process(target, new Command() {
//            @Override
//            public void process(int[] target) {
//                int sum = 0;
//                for(int tmp : target) {
//                    sum += tmp;
//                }
//                System.out.println("数组元素总和为：" + sum);
//            }
//        });
//
//        ProcessArray pa = new ProcessArray();
//        int[] array = {3, -4, 6, 4};
//        pa.process(array , (int[] target) -> {
//            int sum = 0;
//            for(int tmp : target) {
//                sum += tmp;
//            }
//            System.out.println("数组元素总和为：" + sum);
//        });

//        new Thread(()->System.out.println("OK")).start();
//        new Thread(() -> System.out.println("NO")).start();

        List features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
//        features.stream().forEach(item->System.out.println(item));
//        features.stream().forEach(System.out::println);
//        features.stream().filter(item->item.toString().contains("m"));

        features.stream().forEach(System.out::println);
    }

}
