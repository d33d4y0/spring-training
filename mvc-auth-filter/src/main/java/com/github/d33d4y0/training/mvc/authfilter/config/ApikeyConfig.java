package com.github.d33d4y0.training.mvc.authfilter.config;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.github.d33d4y0.training.mvc.authfilter.domain.Apikey;
import com.github.d33d4y0.training.mvc.authfilter.domain.Jwt;
import com.github.d33d4y0.training.mvc.authfilter.helper.YamlPropertySourceFactory;

@Configuration
@ConfigurationProperties
@PropertySource(value = "file:${apikey.config.path}", factory = YamlPropertySourceFactory.class)
public class ApikeyConfig {

	private Map<String, Apikey> apikeys;
	private Map<String, Jwt> jwts;

	public Map<String, Apikey> getApikeys() {
		return apikeys;
	}

	public void setApikeys(Map<String, Apikey> apikeys) {
		this.apikeys = apikeys;
	}

	public Map<String, Jwt> getJwts() {
		return jwts;
	}

	public void setJwts(Map<String, Jwt> jwts) {
		this.jwts = jwts;
	}

}
