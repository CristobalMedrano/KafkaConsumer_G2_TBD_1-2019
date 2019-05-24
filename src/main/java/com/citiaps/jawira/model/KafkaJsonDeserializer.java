package com.citiaps.jawira.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import twitter4j.Twitter;

import java.util.Map;

public class KafkaJsonDeserializer<Tweet> implements Deserializer {

    private Logger logger = LogManager.getLogger(this.getClass());

    private Class <Tweet> type;

    public KafkaJsonDeserializer(Class type) {
        this.type = type;
    }
    @Override
    public void configure(Map configs, boolean isKey) {

    }

    @Override
    public Object deserialize(String s, byte[] bytes) {
        ObjectMapper mapper = new ObjectMapper();
        Tweet obj = null;
        try {
            obj = mapper.readValue(bytes, type);
        } catch (Exception e) {

            logger.error(e.getMessage());
        }
        return obj;
    }

    @Override
    public void close() {

    }
}
