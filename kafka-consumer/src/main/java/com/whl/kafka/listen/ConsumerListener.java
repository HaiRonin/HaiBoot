package com.whl.kafka.listen;

import com.whl.common.bean.User;
import com.whl.common.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ConsumerListener {

    @KafkaListener(topics = "summer")
    public void onMessage(String message){
        //insertIntoDb(buffer);//这里为插入数据库代码
        log.info("onMessage.message={}",message);
    }


    @KafkaListener(topics = "userTopic")
    public void onUserMessage(String message){
        //insertIntoDb(buffer);//这里为插入数据库代码
        log.info("onMessage.message={}",message);
        User user = JsonUtils.jsonToPojo(message,User.class);
    }

    @KafkaListener(id = "consumer", topics = "topic.quick.consumer")
    public void consumerListener(ConsumerRecord<Integer, String> record) {
        log.info("topic.quick.consumer receive : " + record.toString());
    }
}