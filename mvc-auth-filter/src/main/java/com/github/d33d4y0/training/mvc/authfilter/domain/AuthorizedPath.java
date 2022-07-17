package com.github.d33d4y0.training.mvc.authfilter.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpMethod;

public class AuthorizedPath {

	private String path;
	private HttpMethod method;
	private List<String> roles;

	public AuthorizedPath() {

	}

	public AuthorizedPath(String path, HttpMethod method) {
		this.path = path;
		this.method = method;
		this.roles = new ArrayList<>();
	}
	
	public AuthorizedPath(String path, HttpMethod method, String... roles) {
		this.path = path;
		this.method = method;
		this.roles = Arrays.asList(roles);
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public HttpMethod getMethod() {
		return method;
	}

	public void setMethod(HttpMethod method) {
		this.method = method;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

}