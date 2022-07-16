package com.github.d33d4y0.training.mvc.authfilter.domain;

import java.util.List;

public class Apikey {

	public static final String APIKEY_PREFIX = "APIKEY_";
	
	private String role;
	private List<Path> paths;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Path> getPaths() {
		return paths;
	}

	public void setPaths(List<Path> paths) {
		this.paths = paths;
	}

}
