package com.whl.rabbitmq.consumer;


import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RabbitListener(queues = "helloQuery")//监听的队列名称 helloQuery
public class DirectReceiver implements ChannelAwareMessageListener {

      private Map<String,Integer> map = new HashMap<String,Integer>();
//    @RabbitHandler
//    public void process(Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag,
//                        Map testMessage, @Headers Map<String,Object> map) throws IOException {
//        System.out.println("helloQuery消费者收到消息  : " + testMessage.toString());
//
//        System.out.println("tag:"+tag+"map:"+map.toString());
//        //channel.basicAck(tag,false);// 确认消息
//        channel.basicNack((Long)map.get(AmqpHeaders.DELIVERY_TAG),false,true);//手动否认
//
//    }

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        String messageId="";
        int imax = 0;
        try {
            //因为传递消息的时候用的map传递,所以将Map从Message内取出需要做些处理
            String msg = message.toString();
            String[] msgArray = msg.split("'");//可以点进Message里面看源码,单引号直接的数据就是我们的map消息数据
            Map<String, String> msgMap = mapStringToMap(msgArray[1].trim());
            messageId=msgMap.get("messageId");
            String messageData=msgMap.get("messageData");
            String createTime=msgMap.get("createTime");
            System.out.println("messageId:"+messageId+"  messageData:"+messageData+"  createTime:"+createTime);
           //消费者收到消息后，手动调用basic.ack/basic.nack/basic.reject后，RabbitMQ收到这些消息后，才认为本次投递成功
//            channel.basicAck(deliveryTag, false);
            if(map.containsKey(messageId)){
                int max = (int)map.getOrDefault(messageId,1);
                map.put(messageId,max+1);
            }
            else{
                map.put(messageId,1);
            }
            imax = (int)map.getOrDefault(messageId,0);
            if(imax == 2){
                System.out.println(Integer.valueOf("aaaa"));
            }
            if(imax > 3){
                System.out.println("达到最大尝试次数：messageId:"+messageId+"  imax:"+imax);
                System.out.println("messageId:"+messageId+"  imax:"+imax);
                channel.basicNack(deliveryTag,false,false);
                return;
            }
            channel.basicReject(deliveryTag, true);//为true会重新放回队列
//            channel.basicNack(deliveryTag,false,false);
//			channel.basicReject(deliveryTag, true);//为true会重新放回队列
        } catch (Exception e) {
            System.out.println("发送异常重试：messageId:"+messageId+"  imax:"+imax);
//            channel.basicNack(deliveryTag,false,false);
//            channel.basicRecover();
//            e.printStackTrace();
        }
    }

    //{key=value,key=value,key=value} 格式转换成map
    private Map<String, String> mapStringToMap(String str) {
        str = str.substring(1, str.length() - 1);
        String[] strs = str.split(",");
        Map<String, String> map = new HashMap<String, String>();
        for (String string : strs) {
            String key = string.split("=")[0].trim();
            String value = string.split("=")[1];
            map.put(key, value);
        }
        return map;
    }

}
