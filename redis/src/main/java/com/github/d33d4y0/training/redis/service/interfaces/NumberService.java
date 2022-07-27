package com.github.d33d4y0.training.redis.service.interfaces;

import java.util.Map;

public interface NumberService {

	public void putAll(Map<String, Long> map);

	public Long put(String key, long value);

	public Long remove(String key);

	public Boolean exists(String key);

	public Long get(String key);

	public Long incrementAndGet(String key);

	public Long decrementAndGet(String key);

	public Long addAndGet(String key, long delta);
	
	public Long subtractAndGet(String key, long delta);
}
