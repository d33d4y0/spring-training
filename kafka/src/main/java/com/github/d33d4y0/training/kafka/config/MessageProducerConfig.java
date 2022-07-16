package com.github.d33d4y0.training.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class MessageProducerConfig {
	
	@Bean
	public ProducerFactory<String, ?> producerFactory() {
		return new DefaultKafkaProducerFactory<>(producerConfig());
	}

	@Bean
	public Map<String, Object> producerConfig() {
		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return props;
	}

	@Bean
	public KafkaTemplate<String, ?> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}

//	@Bean
//	public ProducerUtils sender() {
//		return new ProducerUtils();
//	}
}
