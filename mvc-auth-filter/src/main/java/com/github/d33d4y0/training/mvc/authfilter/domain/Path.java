package com.github.d33d4y0.training.mvc.authfilter.domain;

import java.util.List;

public class Path {

	private String path;
	private List<String> methods;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<String> getMethods() {
		return methods;
	}

	public void setMethods(List<String> methods) {
		this.methods = methods;
	}

}
