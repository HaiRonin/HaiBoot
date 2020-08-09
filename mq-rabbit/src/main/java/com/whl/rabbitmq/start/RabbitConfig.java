package com.whl.rabbitmq.start;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Author : JCccc
 * @CreateTime : 2019/9/3
 * @Description :
 **/
@Configuration
public class RabbitConfig {
    @Autowired
    private CachingConnectionFactory connectionFactory;


    @Bean
    public RabbitTemplate createRabbitTemplate(){
        //若使用confirm-callback ，必须要配置publisherConfirms 为true
        connectionFactory.setPublisherConfirms(true);
        //若使用return-callback，必须要配置publisherReturns为true
        connectionFactory.setPublisherReturns(true);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        //使用return-callback时必须设置mandatory为true，或者在配置中设置mandatory-expression的值为true
        rabbitTemplate.setMandatory(true);

        // 如果消息没有到exchange,则confirm回调,ack=false; 如果消息到达exchange,则confirm回调,ack=true
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                System.out.println("ConfirmCallback:     "+"相关数据："+correlationData);
                System.out.println("ConfirmCallback:     "+"确认情况："+ack);
                System.out.println("ConfirmCallback:     "+"原因："+cause);

            }
        });

        //  //如果exchange到queue成功,则不回调return;如果exchange到queue失败,则回调return(需设置mandatory=true,否则不回回调,消息就丢了)
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                System.out.println("ReturnCallback:     "+"消息："+message);
                System.out.println("ReturnCallback:     "+"回应码："+replyCode);
                System.out.println("ReturnCallback:     "+"回应信息："+replyText);
                System.out.println("ReturnCallback:     "+"交换机："+exchange);
                System.out.println("ReturnCallback:     "+"路由键："+routingKey);
            }
        });

        return rabbitTemplate;
    }

    /***
     * 到这里，生产者推送消息的消息确认调用回调函数已经完毕。
     * 可以看到上面写了两个回调函数，一个叫 ConfirmCallback ，一个叫 RetrunCallback；
     * 那么以上这两种回调函数都是在什么情况会触发呢？
     *
     * 先从总体的情况分析，推送消息存在四种情况：
     *
     * ①消息推送到server，但是在server里找不到交换机
     * ②消息推送到server，找到交换机了，但是没找到队列
     * ③消息推送到sever，交换机和队列啥都没找到
     * ④消息推送成功
     * ————————————————
     * 版权声明：本文为CSDN博主「小目标青年」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
     * 原文链接：https://blog.csdn.net/qq_35387940/article/details/100514134
     */
}
