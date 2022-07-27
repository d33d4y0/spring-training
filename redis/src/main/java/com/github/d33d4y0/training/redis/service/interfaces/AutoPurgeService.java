package com.github.d33d4y0.training.redis.service.interfaces;

import java.util.Map;
import java.util.Set;

public interface AutoPurgeService {

	public Void putAll(Map<String, Object> map, int purgeTime);

	public Void put(String key, Object value, int purgeTime, boolean ignoreExists);

	public Object remove(String key, boolean ignoreNotFound);

	public Boolean exists(String key);

	public Object get(String key);

	public Map<String, Object> all();

	public Set<String> keys();

	public Void clear();

	public Map<String, Integer> purgeMap();
}
