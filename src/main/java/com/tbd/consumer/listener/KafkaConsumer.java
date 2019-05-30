package com.tbd.consumer.listener;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import com.tbd.consumer.model.Tweet;
import org.bson.Document;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    MongoClient mongoClient = MongoClients.create("mongodb://kafkaApi:kafkatbd2019@localhost:27017/?authSource=ligaChilenaDB");
    MongoDatabase data = mongoClient.getDatabase("ligaChilenaDB");
    MongoCollection<Document> tweetCollection = data.getCollection("ligaTweets");

    @KafkaListener(topics = "${kafka.topic}", groupId = "${zookeeper.group-id}", containerFactory = "kafkaListenerFactory")
    public void consumer(Tweet tweet)
    {
        Gson gson = new Gson();
        String json = gson.toJson(tweet);
        BasicDBObject document = (BasicDBObject) JSON.parse(json);
        tweetCollection.insertOne(new Document(document));

    }
}
