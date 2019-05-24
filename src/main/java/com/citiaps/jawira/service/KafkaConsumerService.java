package com.citiaps.jawira.service;

import com.citiaps.jawira.model.Tweet;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "twitterJsonChile3", groupId = "twitter_consumer",containerFactory = "kafkaListener")
    public void consume(Tweet tweet){
        System.out.println("Consumed Message :"+tweet);
    }
}
