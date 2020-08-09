package com.whl.rabbitmq.controller;

import com.whl.rabbitmq.consumer.DirectReceiver2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class RabbitmqController {

    @Autowired
    RabbitTemplate rabbitTemplate;  //使用RabbitTemplate,这提供了接收/发送等等方法
    @Autowired
    DirectReceiver2 directReceiver2;
    @GetMapping("/sendDirectMessage")
    @ResponseBody
    public String sendDirectMessage() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message hello!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        for (int i=0;i<5;i++){
            map.put("index", i);
            rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRouting", map);
        }
       return "ok";
    }

    @GetMapping("/sendhelloQuery")
    @ResponseBody
    public String sendhelloQuery() {
        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        for (int i=0;i<1;i++) {
            String messageId = String.valueOf(UUID.randomUUID());
            String messageData = "directExchange message helloQuery!";
            String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            Map<String, Object> map = new HashMap<>();
            map.put("messageId", messageId);
            map.put("messageData", messageData);
            map.put("createTime", createTime);
            rabbitTemplate.convertAndSend("TestDirectExchange", "helloQuery", map);
        }
        return "ok";
    }

    @GetMapping("/sendTopicMessage1")
    @ResponseBody
    public String sendTopicMessage1() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: M A N ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> manMap = new HashMap<>();
        manMap.put("messageId", messageId);
        manMap.put("messageData", messageData);
        manMap.put("createTime", createTime);
        rabbitTemplate.convertAndSend("topicExchange", "topic.man", manMap);
        return "ok";
    }

    @GetMapping("/sendTopicMessage2")
    @ResponseBody
    public String sendTopicMessage2() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: woman is all ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> womanMap = new HashMap<>();
        womanMap.put("messageId", messageId);
        womanMap.put("messageData", messageData);
        womanMap.put("createTime", createTime);
        rabbitTemplate.convertAndSend("topicExchange", "topic.woman", womanMap);
        return "ok";
    }

    @GetMapping("/sendFanoutMessage")
    @ResponseBody
    public String sendFanoutMessage() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: testFanoutMessage ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        rabbitTemplate.convertAndSend("fanoutExchange", null, map);
        return "ok";
    }


    @GetMapping("/getCount")
    @ResponseBody
    public String getCount() {
        return directReceiver2.getMap().toString();
    }
}
