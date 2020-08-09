package com.whl.sharding;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Description: 启动类
 *
 * @author xub
 * @date 2019/10/10 下午8:53
 */
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@MapperScan("com.whl.sharding.dao")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
