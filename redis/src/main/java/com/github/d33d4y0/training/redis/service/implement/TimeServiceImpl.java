package com.github.d33d4y0.training.redis.service.implement;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.github.d33d4y0.training.redis.service.interfaces.TimeService;

@Service
public class TimeServiceImpl implements TimeService{

//	TODO upcoming feature
	
	
	@Override
	public Void putAll(Map<String, LocalDateTime> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocalDateTime put(String key, boolean ignoreExists) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocalDateTime remove(String key, boolean ignoreNotFound) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean exists(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocalDateTime get(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, LocalDateTime> all() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> keys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void clear() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> today() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> yesterday() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> fromYesterday() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> last15Mins() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> last30Mins() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> lastHour() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> last7Days() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> last15Days() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> thisMonth() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> from(LocalDateTime from) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> fromTo(LocalDateTime from, LocalDateTime to) {
		// TODO Auto-generated method stub
		return null;
	}

}
