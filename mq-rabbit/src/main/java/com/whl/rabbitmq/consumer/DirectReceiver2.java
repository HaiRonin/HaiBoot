package com.whl.rabbitmq.consumer;


import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Component
@RabbitListener(queues = "TestDirectQueue")//监听的队列名称 TestDirectQueue
public class DirectReceiver2 {
    private static Logger log = LoggerFactory.getLogger(DirectReceiver2.class);
    private Map<String,Integer> map = new HashMap<String,Integer>();
    AtomicLong al = new AtomicLong(0);
    @RabbitHandler
    public void process(Map testMessage, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag)
            throws IOException {

        al.incrementAndGet();
        String index = testMessage.get("index").toString();
//        log.info("TestDirectQueue.al={},tag={},index={}",al.get(),tag,index);
        try {
            if(map.containsKey(index)){
                int max = (int)map.getOrDefault(index,0);
                map.put(index,max+1);
            }
            else{
                map.put(index,0);
            }
            int it = map.getOrDefault(index,0);
            log.info("TestDirectQueue.index={}，it={}",index,map.getOrDefault(index,0));
            if(index.equals("1")){
                if(channel.isOpen()){
                    channel.basicNack(tag,false,false);
                }

            }
            else{
                if(channel.isOpen()){
                    channel.basicAck(tag,false);
                }


            }
//            if(it < 2){
//                if(channel.isOpen()){
//                    channel.basicNack(tag,false,false);
////                    log.info("TestDirectQueue.al={},tag={},index={}，ack={}",al.get(),tag,index,"basicNack");
//                }
//
//            }
//            else{
//                if(channel.isOpen()){
//                    channel.basicAck(tag,false);
////                    log.info("TestDirectQueue.al={},tag={},index={}，ack={}",al.get(),tag,index,"basicAck");
//                }
//
//            }

        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    public Map<String, Integer> getMap() {
        return map;
    }

    public void setMap(Map<String, Integer> map) {
        this.map = map;
    }
}
