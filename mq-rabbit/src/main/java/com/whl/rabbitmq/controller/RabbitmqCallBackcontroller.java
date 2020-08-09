package com.whl.rabbitmq.controller;

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

/***
 * 先从总体的情况分析，推送消息存在四种情况：
 *
 * ①消息推送到server，但是在server里找不到交换机
 * ②消息推送到server，找到交换机了，但是没找到队列
 * ③消息推送到sever，交换机和队列啥都没找到
 * ④消息推送成功
 */
@Controller
public class RabbitmqCallBackcontroller {

    @Autowired
    RabbitTemplate rabbitTemplate;  //使用RabbitTemplate,这提供了接收/发送等等方法

    /***
     * ①消息推送到server，但是在server里找不到交换机
     * 写个测试接口，把消息推送到名为‘non-existent-exchange’的交换机上（这个交换机是没有创建没有配置的）：
     * @return
     */
    @GetMapping("/TestMessageAck")
    @ResponseBody
    public String TestMessageAck() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: non-existent-exchange test message ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        try {
            rabbitTemplate.convertAndSend("non-existent-exchange", "TestDirectRouting", map);
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return "ok";
    }


    /***
     * ②消息推送到server，找到交换机了，但是没找到队列
     * 这种情况就是需要新增一个交换机，但是不给这个交换机绑定队列，我来简单地在DirectRabitConfig里面新增一个直连交换机，名叫‘lonelyDirectExchange’，但没给它做任何绑定配置操作：
     * @return
     */
    @GetMapping("/TestMessageAck2")
    @ResponseBody
    public String TestMessageAck2() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: lonelyDirectExchange test message ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        try {
            rabbitTemplate.convertAndSend("lonelyDirectExchange", "TestDirectRouting", map);
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return "ok";
    }

}
