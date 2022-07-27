package com.github.d33d4y0.training.redis.service.implement;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.github.d33d4y0.training.redis.service.interfaces.CollectionService;

@Service
public class ListServiceImpl implements CollectionService {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	@Resource(name = "redisTemplate")
	private ListOperations<String, Object> listOps;
	
	@Override
	public void putAll(Map<String, Collection<Object>> map) {
		for (Entry<String, Collection<Object>> entry : map.entrySet()) {
			listOps.rightPushAll(entry.getKey(), entry.getValue());
		}
	}

	@Override
	public void put(String key, Object value) {
		listOps.rightPush(key, value);
	}

	@Override
	public void remove(String key, Object value) {
		listOps.remove(key, 1, value);
	}

	@Override
	public Object pop(String key) {
		return listOps.rightPop(key);
	}

	@Override
	public List<Object> pops(String key, long size) {
		return listOps.rightPop(key, size);
	}

	@Override
	public Collection<Object> removeKey(String key) {
		long size = listOps.size(key);
		List<Object> all = listOps.range(key, 0, size);
		redisTemplate.delete(key);
		return all;
	}

	@Override
	public void putKey(String key, Collection<Object> list) {
		listOps.rightPushAll(key, list);
	}

	@Override
	public Boolean exists(String key) {
		return listOps.size(key) > 0 ? true : false;
	}

	@Override
	public Boolean valueExists(String key, Object value) {
		return listOps.indexOf(key, value) == null ? false : true;
	}

	@Override
	public Collection<Object> get(String key) {
		long size = listOps.size(key);
		return listOps.range(key, 0, size);
	}

}
