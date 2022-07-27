package com.github.d33d4y0.training.redis.service.implement;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.github.d33d4y0.training.redis.service.interfaces.NumberService;

@Service
public class NumberServiceImpl implements NumberService {

	@Resource(name = "redisTemplate")
	private ValueOperations<String, Long> longOps;

	@Override
	public void putAll(Map<String, Long> map) {
		longOps.multiSet(map);
	}

	@Override
	public Long put(String key, long value) {
		longOps.set(key, value);
		return value;
	}

	@Override
	public Long remove(String key) {
		return longOps.getAndDelete(key);
	}

	@Override
	public Boolean exists(String key) {
		return longOps.get(key) != null ? true : false;
	}

	@Override
	public Long get(String key) {
		return longOps.get(key);
	}

	@Override
	public Long incrementAndGet(String key) {
		return longOps.increment(key);
	}

	@Override
	public Long decrementAndGet(String key) {
		return longOps.decrement(key);
	}

	@Override
	public Long addAndGet(String key, long delta) {
		return longOps.increment(key, delta);
	}

	@Override
	public Long subtractAndGet(String key, long delta) {
		return longOps.decrement(key, delta);
	}

}
