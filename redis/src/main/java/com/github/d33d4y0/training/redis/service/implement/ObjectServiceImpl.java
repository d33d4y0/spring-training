package com.github.d33d4y0.training.redis.service.implement;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.github.d33d4y0.training.redis.service.interfaces.ObjectService;

@Service
public class ObjectServiceImpl implements ObjectService{

	@Resource(name = "redisTemplate")
	private ValueOperations<String, Object> objOps;
	
	@Override
	public void putAll(Map<String, Object> map) {
		objOps.multiSet(map);
	}

	@Override
	public void put(String key, Object value) {
		objOps.set(key, value);
	}

	@Override
	public Object remove(String key) {
		return objOps.getAndDelete(key);
	}

	@Override
	public Boolean exists(String key) {
		return objOps.get(key) != null ? true : false;
	}

	@Override
	public Object get(String key) {
		return objOps.get(key);
	}

}
