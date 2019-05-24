package com.citiaps.jawira.config;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.citiaps.jawira.model.KafkaJsonDeserializer;
import com.citiaps.jawira.model.Tweet;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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

	/*@Bean
	public Consumer<String, String> kafkaConsumer() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapServers);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaJsonDeserializer.class);
		props.put(ConsumerConfig.GROUP_ID_CONFIG,zookeeperGroupId);
		return new KafkaConsumer<String, String>(props);
	}*/

	/*@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Tweet> kafkaListener() {
		ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory();
		factory.setConsumerFactory(consumerFactory());

		return factory;
	}*/
	@Primary
	@Bean
	public Properties consumerProperties() {
		Properties props=new Properties();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapServers);
		props.put(ConsumerConfig.GROUP_ID_CONFIG,zookeeperGroupId);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaJsonDeserializer.class);
		return(props);
	}
	@Bean
	public Consumer<Long, String> kafkaConsumer(Properties props){
		Consumer<Long, String> consumer = new KafkaConsumer<>(props);
		consumer.subscribe(Collections.singletonList(kafkaTopics));
		return consumer;
	}

}
