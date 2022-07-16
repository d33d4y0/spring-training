package com.github.d33d4y0.training.kafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.d33d4y0.training.kafka.domain.EmailNotificationDomain;
import com.github.d33d4y0.training.kafka.utils.ProducerUtils;

@Service
public class ProducerService {

	@Autowired
	private ProducerUtils producerUtils;
	
	public void emailNotify(EmailNotificationDomain noti) {
		producerUtils.send(null, noti);
	}
}
