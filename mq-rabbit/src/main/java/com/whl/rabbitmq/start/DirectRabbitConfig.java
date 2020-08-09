package com.whl.rabbitmq.start;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DirectRabbitConfig {
    private static Logger log = LoggerFactory.getLogger(DirectRabbitConfig.class);
    //队列 起名：TestDirectQueue
    @Bean
    public Queue TestDirectQueue() {
        return new Queue("TestDirectQueue",true);  //true 是否持久
    }

    @Bean
    public Queue TestHelloQueryQueue() {
        return new Queue("helloQuery",true);  //true 是否持久
    }

    //Direct交换机 起名：TestDirectExchange
    @Bean
    DirectExchange TestDirectExchange() {
        return new DirectExchange("TestDirectExchange");
    }

    /***
     * RabbitmqCallBackcontroller c测试使用
     * ②消息推送到server，找到交换机了，但是没找到队列
     * 这种情况就是需要新增一个交换机，但是不给这个交换机绑定队列，我来简单地在DirectRabitConfig里面新增一个直连交换机，名叫‘lonelyDirectExchange’，但没给它做任何绑定配置操作：
     * @return
     */
    @Bean
    DirectExchange lonelyDirectExchange() {
        return new DirectExchange("lonelyDirectExchange");
    }

    //绑定  将队列和交换机绑定, 并设置用于匹配键：TestDirectRouting
    @Bean
    Binding bindingDirect() {
        return BindingBuilder.bind(TestDirectQueue()).to(TestDirectExchange()).with("TestDirectRouting");
    }



    //绑定  将队列和交换机绑定, 并设置用于匹配键：TestDirectRouting
    @Bean
    Binding bindingDirect2() {
        return BindingBuilder.bind(TestHelloQueryQueue()).to(TestDirectExchange()).with("helloQuery");
    }

}
