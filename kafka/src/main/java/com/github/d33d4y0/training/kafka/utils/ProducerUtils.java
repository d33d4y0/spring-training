package com.github.d33d4y0.training.kafka.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.github.d33d4y0.training.kafka.domain.EmailNotificationDomain;

@Service
public class ProducerUtils {

	protected static Logger log = LoggerFactory.getLogger(ProducerUtils.class);
	
	@Autowired
	protected KafkaTemplate<String, EmailNotificationDomain> kafkaTemplate;

	public void send(String topicName, String key, EmailNotificationDomain message) {
		ListenableFuture<SendResult<String, EmailNotificationDomain>> future = kafkaTemplate.send(topicName, key, message);

		future.addCallback(new ListenableFutureCallback<SendResult<String, EmailNotificationDomain>>() {
			@Override
			public void onSuccess(SendResult<String, EmailNotificationDomain> result) {
				log.info("Sent message to target=[" + message.getTarget() + "] with offset=[" + result.getRecordMetadata().offset() + "]");
			}

			@Override
			public void onFailure(Throwable ex) {
				log.info("Unable to send message to target=[" + message.getTarget() + "] due to : " + ex.getMessage());
			}
		});
	}
	
	public void send(String key, EmailNotificationDomain message) {
		send("email-notification-topic", key, message);
	}
}
