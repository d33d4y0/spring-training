package com.github.d33d4y0.training.mvc.authfilter.domain;

import java.util.List;

public class Jwt {

	public static final String JWT_PREFIX = "JWT_";
	private List<Path> paths;

	public List<Path> getPaths() {
		return paths;
	}

	public void setPaths(List<Path> paths) {
		this.paths = paths;
	}

}
