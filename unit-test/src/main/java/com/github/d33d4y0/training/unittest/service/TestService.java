package com.github.d33d4y0.training.unittest.service;

import java.util.Map;

public interface TestService {

	public int add(int a, int b);

	public int subtract(int a, int b);

	public Map<String, String> reflectAsJson(String name);

	public String reflectAsString(String str);

}
