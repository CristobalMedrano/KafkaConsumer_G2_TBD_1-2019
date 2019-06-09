package com.tbd.consumer.repository;

import com.tbd.consumer.model.Tweet;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface TweetRepository extends ElasticsearchRepository<Tweet, Long> { }
