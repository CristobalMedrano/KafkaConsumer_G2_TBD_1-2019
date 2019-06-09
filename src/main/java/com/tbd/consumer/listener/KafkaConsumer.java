package com.tbd.consumer.listener;

import com.tbd.consumer.Analisis.Classifier;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import com.tbd.consumer.model.Tweet;
import com.tbd.consumer.model.TweetDate;
import com.tbd.consumer.model.TweetNoDate;
import com.tbd.consumer.repository.TweetRepository;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class KafkaConsumer {

    @Autowired
    private Classifier classifier;

    private HashMap<String, Double> stadistics = new HashMap<String, Double>();

    TweetRepository tweetRepository;
    MongoClient mongoClient = MongoClients.create("mongodb://kafkaApi:kafkatbd2019@localhost:27017/?authSource=ligaChilenaDB");
    MongoDatabase data = mongoClient.getDatabase("ligaChilenaDB");
    MongoCollection<Document> tweetCollection = data.getCollection("test");

    Date newDate = new Date();
    long curTimeInMs = newDate.getTime() - (16*24*60* 60000) - (60000*60*(1)) - 60000*40;

    @KafkaListener(topics = "${kafka.topic}", groupId = "${zookeeper.group-id}", containerFactory = "kafkaListenerFactory")
    public void consumer(TweetNoDate tweetNoDate)
    {
        stadistics = classifier.classify(tweetNoDate.getText());


        newDate = addMinutesToDate(1 , curTimeInMs);
        curTimeInMs = newDate.getTime();
        DateFormat formatter = new SimpleDateFormat("MMM dd, YYYY, hh:mm:ss a", Locale.US);
        String s = formatter.format(newDate);
        Date fakeDate = new Date(s);

        Tweet tweet = new Tweet(
                tweetNoDate.getId(),
                stadistics.get("positive"),
                stadistics.get("negative"),
                tweetNoDate.getName(),
                tweetNoDate.getText(),
                tweetNoDate.getLike(),
                tweetNoDate.getFollowers(),
                tweetNoDate.getGeoLocation(),
                tweetNoDate.getUserLocation(),
                tweetNoDate.getRetweet(),
                fakeDate);
        System.out.println(tweet);
        Gson gson = new Gson();
        String json = gson.toJson(tweet);
        BasicDBObject document = (BasicDBObject) JSON.parse(json);
        System.out.println(json);
        tweetCollection.insertOne(new Document(document));
        tweetRepository.save(tweet);

    }

    private static Date addMinutesToDate(int minutes, long timeInMs){
        final long ONE_MINUTE_IN_MILLIS = ThreadLocalRandom.current().nextLong(15000, 17000);//millisecs

        Date afterAddingMins = new Date(timeInMs + (minutes * ONE_MINUTE_IN_MILLIS));
        return afterAddingMins;
    }
}
