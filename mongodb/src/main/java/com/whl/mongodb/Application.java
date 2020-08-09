package com.whl.mongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Description: 启动类
 *
 * @author xub
 * @date 2019/10/10 下午8:53
 */
@SpringBootApplication
@EnableEurekaClient
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
