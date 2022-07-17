package com.github.d33d4y0.training.mvc.authfilter.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.github.d33d4y0.training.mvc.authfilter.domain.AuthorizedPath;
import com.github.d33d4y0.training.mvc.authfilter.helper.YamlPropertySourceFactory;

@Configuration
@ConfigurationProperties
@PropertySource(value = "file:${authorized.config.path}", factory = YamlPropertySourceFactory.class)
public class AuthorizedConfig {

	private List<AuthorizedPath> authorized;

	public List<AuthorizedPath> getAuthorized() {
		return authorized;
	}

	public void setAuthorized(List<AuthorizedPath> authorized) {
		this.authorized = authorized;
	}

}
