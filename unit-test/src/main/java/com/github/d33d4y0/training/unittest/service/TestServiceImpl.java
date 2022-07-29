package com.github.d33d4y0.training.unittest.service;

import java.util.Collections;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

	@Override
	public int add(int a, int b) {
		return a+b;
	}

	@Override
	public int subtract(int a, int b) {
		return a-b;
	}

	@Override
	public Map<String, String> reflectAsJson(String name) {
		return Collections.singletonMap("name", name);
	}

	@Override
	public String reflectAsString(String str) {
		return str;
	}


}
