package com.github.d33d4y0.training.kafka.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.d33d4y0.training.kafka.domain.EmailNotificationDomain;

@Service
public class ConsumerService {

	protected static Logger log = LoggerFactory.getLogger(ConsumerService.class);
	
//	@KafkaListener(topics = "email-notification-topic")
//	public void listen(ConsumerRecord<String, EmailNotificationDomain> cr) {
////		Sending email. . .
//	}
	
	@KafkaListener(topics = "email-notification-topic")
	public void listen(EmailNotificationDomain noti) throws JsonProcessingException {
		log.info("notify to: [{}] with content: [{}]", noti.getTarget(), noti.getContent());
//		Sending email. . .
	}
}
