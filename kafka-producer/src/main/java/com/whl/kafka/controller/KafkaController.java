package com.whl.kafka.controller;

import com.whl.common.bean.User;
import com.whl.common.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class KafkaController {

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    @GetMapping("/message/send")
    @ResponseBody
    public boolean send(@RequestParam String message){
        kafkaTemplate.send("summer",message);
        return true;
    }

    @PostMapping("/user/save")
    @ResponseBody
    public boolean saveUser(@RequestBody User user){
        for(int i=0;i<100;i++){
            kafkaTemplate.send("userTopic", JsonUtils.objectToJson(user));
        }
        return true;
    }

    @PostMapping("/message/test")
    @ResponseBody
    public boolean testConsumerRecord() {
        kafkaTemplate.send("topic.quick.consumer", "test receive by consumerRecord");
        return true;
    }

    @PostMapping("/message/testBatch")
    @ResponseBody
    public void testBatch() {
        for (int i = 0; i < 12; i++) {
            kafkaTemplate.send("topic.quick.batch", "test batch listener,dataNum-" + i);
        }
    }

    @PostMapping("/message/testBatchPartition")
    @ResponseBody
    public void testBatchPartition() throws InterruptedException {
        for (int i = 0; i < 12; i++) {
            kafkaTemplate.send("topic.quick.batch.partition", "test batch listener,dataNum-" + i);
        }
    }

    @PostMapping("/message/testAck")
    @ResponseBody
    public void testAck() throws InterruptedException {
        kafkaTemplate.send("topic.quick.ack", "My message,time");
    }
}