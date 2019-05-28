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

    MongoClient mongoClient = MongoClients.create();
    MongoDatabase data = mongoClient.getDatabase("ligaChilenaDB");
    MongoCollection<Document> tweetCollection = data.getCollection("ligaChilena");

    @KafkaListener(topics = "${kafka.topic}", groupId = "${zookeeper.group-id}", containerFactory = "tweetKafkaListenerFactory")
    public void consumer(Tweet tweet)
    {
        Gson gson = new Gson();
        String json = gson.toJson(tweet);
        BasicDBObject document = (BasicDBObject) JSON.parse(json);
        tweetCollection.insertOne(new Document(document));

        /*Document doc = new Document.parse(json);
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
        System.out.println(doc.toString());*/
        //tweetCollection.insertOne();
        //System.out.println("Consume message"+ tweet.toString());
    }
}
