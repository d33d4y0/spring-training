package com.github.d33d4y0.training.mvc.authfilter.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.github.d33d4y0.training.mvc.authfilter.config.AuthConfig;

@Service
public class AuthService {

	@Autowired
	private AuthConfig authConfig;
	
	public static final JsonMapper JSON_MAPPER = JsonMapper.builder()
			.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
			.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true)
			.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true).serializationInclusion(Include.ALWAYS)
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).build();

	static {
		JSON_MAPPER.findAndRegisterModules();
	}
	
	@PostConstruct
	private void init() throws JsonProcessingException {
		System.out.println(JSON_MAPPER.writeValueAsString(authConfig.getApikeys()));
		System.out.println(JSON_MAPPER.writeValueAsString(authConfig.getJwts()));
	}
}
