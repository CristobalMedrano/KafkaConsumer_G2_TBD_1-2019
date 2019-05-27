package com.tbd.consumer.listener;

import com.google.gson.Gson;
import com.mongodb.Mongo;
import com.tbd.consumer.model.Tweet;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

@Service
public class KafkaConsumer {

    /*MongoClient mongo = new MongoClient("localhost",27017);
    */
    Mongo mongo = new Mongo("localhost", 27017);
    DB db = mongo.getDB("ligaChilenaDB");
    DBCollection dbCollection = db.getCollection("ligaChilena");

    @KafkaListener(topics = "${kafka.topic}", groupId = "${zookeeper.group-id}", containerFactory = "tweetKafkaListenerFactory")
    public void consumer(Tweet tweet)
    {
        Gson gson = new Gson();
        String json = gson.toJson(tweet);
        BasicDBObject basicDBObject = new BasicDBObject("Tweet", json );
        dbCollection.insert(basicDBObject);
        //System.out.println("Consume"+ tweet.toString());

    }
}
