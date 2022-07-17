package com.github.d33d4y0.training.mvc.authfilter.domain;

import java.util.List;

public class AuthorizedPath {

	private String path;
	private List<String> methods;
	private List<String> apikeyUser;
	private List<String> jwtUser;

	public AuthorizedPath() {

	}

	public AuthorizedPath(String path, List<String> methods) {
		this.path = path;
		this.methods = methods;
	}

	public AuthorizedPath(String path, List<String> methods, List<String> apikeyUser, List<String> jwtUser) {
		super();
		this.path = path;
		this.methods = methods;
		this.apikeyUser = apikeyUser;
		this.jwtUser = jwtUser;
	}

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

	public List<String> getApikeyUser() {
		return apikeyUser;
	}

	public void setApikeyUser(List<String> apikeyUser) {
		this.apikeyUser = apikeyUser;
	}

	public List<String> getJwtUser() {
		return jwtUser;
	}

	public void setJwtUser(List<String> jwtUser) {
		this.jwtUser = jwtUser;
	}

}