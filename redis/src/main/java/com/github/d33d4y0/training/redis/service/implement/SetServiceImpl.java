package com.github.d33d4y0.training.redis.service.implement;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;

import com.github.d33d4y0.training.redis.service.interfaces.CollectionService;

@Service
public class SetServiceImpl implements CollectionService {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	@Resource(name = "redisTemplate")
	private SetOperations<String, Object> setOps;

	@Override
	public void putAll(Map<String, Collection<Object>> map) {
		for (Entry<String, Collection<Object>> entry : map.entrySet()) {
			setOps.add(entry.getKey(), entry.getValue());
		}
	}

	@Override
	public void put(String key, Object value) {
		setOps.add(key, value);
	}

	@Override
	public void remove(String key, Object value) {
		setOps.remove(key, value);
	}

	@Override
	public Set<Object> removeKey(String key) {
		Set<Object> all = setOps.members(key);
		redisTemplate.delete(key);
		return all;
	}

	@Override
	public void putKey(String key, Collection<Object> list) {
		setOps.add(key, list);
	}

	@Override
	public Boolean exists(String key) {
		return setOps.size(key) > 0 ? true : false;
	}

	@Override
	public Boolean valueExists(String key, Object value) {
		return setOps.isMember(key, value);
	}

	@Override
	public Set<Object> get(String key) {
		return setOps.members(key);
	}

	@Override
	public Object pop(String key) {
		return setOps.pop(key);
	}

	@Override
	public Set<Object> pops(String key, long size) {
		return new HashSet<>(setOps.pop(key, size));
	}

}
