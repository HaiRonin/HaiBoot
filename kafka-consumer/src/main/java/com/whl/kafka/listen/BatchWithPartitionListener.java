package com.whl.kafka.listen;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class BatchWithPartitionListener {
    @Bean
    public NewTopic batchWithPartitionTopic() {
        return new NewTopic("topic.quick.batch.partition", 8, (short) 1);
    }

    @KafkaListener(id = "batchWithPartition",clientIdPrefix = "bwp",containerFactory = "batchContainerFactory",
            topicPartitions = {
                    @TopicPartition(topic = "topic.quick.batch.partition",partitions = {"1","3"}),
                    @TopicPartition(topic = "topic.quick.batch.partition",partitions = {"0","4"},
                                    partitionOffsets = @PartitionOffset(partition = "2",initialOffset = "100")
                                    )
            }
    )
    public void batchListenerWithPartition(List<String> data) {
        log.info("topic.quick.batch.partition  receive : ");
        for (String s : data) {
            log.info(s);
        }
    }

}