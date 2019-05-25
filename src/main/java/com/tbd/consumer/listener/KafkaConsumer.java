package com.tbd.consumer.listener;

import com.tbd.consumer.model.Tweet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    @Value("${zookeeper.group-id}")
    private static String zookeeperGroupId;
    @KafkaListener(topics = "twitterJsonChile6", groupId = "0", containerFactory = "tweetKafkaListenerFactory")
    public void consumer(Tweet tweet){
        System.out.println("Consume message"+ tweet.toString());
    }
}
