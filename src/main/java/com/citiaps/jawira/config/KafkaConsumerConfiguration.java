package com.citiaps.jawira.config;

import java.util.HashMap;
import java.util.Map;

import com.citiaps.jawira.model.Tweet;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.apache.kafka.connect.json.JsonDeserializer;

@EnableKafka
@Configuration
public class KafkaConsumerConfiguration {
	@Value("${kafka.bootstrap-servers}")
	private String kafkaBootstrapServers;
	@Value("${kafka.topic}")
	private String kafkaTopics;
	@Value("${zookeeper.group-id}")
	private String zookeeperGroupId;

	@Bean
	public ConsumerFactory<String, Tweet> consumerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapServers);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		props.put(ConsumerConfig.GROUP_ID_CONFIG,zookeeperGroupId);
		return new DefaultKafkaConsumerFactory(props, new StringDeserializer(),
				new JsonDeserializer());
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Tweet> kafkaListener() {
		ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory();
		factory.setConsumerFactory(consumerFactory());

		return factory;
	}
}
