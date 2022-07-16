package com.github.d33d4y0.training.kafka.domain;

public class EmailNotificationDomain {

	private String target;
	private String content;

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
