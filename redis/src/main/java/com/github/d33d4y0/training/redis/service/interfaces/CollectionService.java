package com.github.d33d4y0.training.redis.service.interfaces;

import java.util.Collection;
import java.util.Map;

public interface CollectionService {

	public void putAll(Map<String, Collection<Object>> map);

	public void put(String key, Object value);

	public void remove(String key, Object value);

	public Object pop(String key);

	public Collection<Object> pops(String key, long size);

	public Collection<Object> removeKey(String key);

	public void putKey(String key, Collection<Object> list);

	public Boolean exists(String key);

	public Boolean valueExists(String key, Object value);

	public Collection<Object> get(String key);

}
