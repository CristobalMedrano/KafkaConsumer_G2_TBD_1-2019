package com.tbd.consumer.listener;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.tbd.consumer.model.Tweet;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

@Service
public class KafkaConsumer {
    @Value("${zookeeper.group-id}")
    private static String zookeeperGroupId;
    MongoClient mongoClient = MongoClients.create();
    MongoDatabase data = mongoClient.getDatabase("twitter");
    //hola
    MongoCollection<Document> tweetCollection = data.getCollection("twitt");
    @KafkaListener(topics = "twitterJsonChile", groupId = "0", containerFactory = "tweetKafkaListenerFactory")
    public void consumer(Tweet tweet){
        Document doc = new Document();
        doc.put("id", tweet.getId());
        doc.put("name", tweet.getName());
        doc.put("text", tweet.getText());
        doc.put("like", tweet.getLike());
        doc.put("followers", tweet.getFollowers());
        doc.put("geoLocation", tweet.getGeoLocation());
        doc.put("userLocation", tweet.getUserLocation());
        doc.put("retweet", tweet.getRetweet());
        doc.put("publicationDate", tweet.getPublicationDate());
        tweetCollection.insertOne(doc);
        System.out.println(doc.toString());
        //tweetCollection.insertOne();
        //System.out.println("Consume message"+ tweet.toString());
    }
}
