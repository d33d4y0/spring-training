package com.github.d33d4y0.training.redis.service.implement;

import java.util.Collection;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.github.d33d4y0.training.redis.service.interfaces.CollectionService;

@Service
public class SortedListServiceImpl implements CollectionService {

//	TODO upcoming feature
	
	@Override
	public void putAll(Map<String, Collection<Object>> map) {
		// TODO Auto-generated method stub

	}

	@Override
	public void put(String key, Object value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(String key, Object value) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object pop(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Object> pops(String key, long size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Object> removeKey(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putKey(String key, Collection<Object> list) {
		// TODO Auto-generated method stub

	}

	@Override
	public Boolean exists(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean valueExists(String key, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Object> get(String key) {
		// TODO Auto-generated method stub
		return null;
	}

}
