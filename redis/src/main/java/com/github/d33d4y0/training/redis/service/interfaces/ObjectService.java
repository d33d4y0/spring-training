package com.github.d33d4y0.training.redis.service.interfaces;

import java.util.Map;

public interface ObjectService {

	public void putAll(Map<String, Object> map);

	public void put(String key, Object value);

	public Object remove(String key);

	public Boolean exists(String key);

	public Object get(String key);
}
