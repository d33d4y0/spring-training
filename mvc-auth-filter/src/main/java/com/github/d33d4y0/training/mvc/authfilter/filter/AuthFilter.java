package com.github.d33d4y0.training.mvc.authfilter.filter;

import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.stereotype.Component;

import com.github.d33d4y0.training.mvc.authfilter.domain.Apikey;
import com.github.d33d4y0.training.mvc.authfilter.domain.Jwt;

@Component
public class AuthFilter extends AbstractPreAuthenticatedProcessingFilter {

	@Autowired
	private AuthManager authManager;

	public static final String APIKEY_HEADER = "apikey";
	public static final String JWT_HEADER = "jwt";

	@PostConstruct
	private void init() {
		setAuthenticationManager(authManager);
	}

	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		Object obj = "";
		List<String> list = Collections.list(request.getHeaderNames());
		if (list.contains(APIKEY_HEADER)) {
			obj += ";" + Apikey.APIKEY_PREFIX + request.getHeader(APIKEY_HEADER);
		}
		if (list.contains(JWT_HEADER)) {
			obj += ";" + Jwt.JWT_PREFIX + request.getHeader(JWT_HEADER);
		}
		return obj.toString().isEmpty() ? obj : obj.toString().substring(1);
	}

//	Cookie version
//	@Override
//	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
//		Object obj = null;
//		Cookie[] cookies = request.getCookies();
//		for (Cookie cookie : cookies) {
//			if (APIKEY_HEADER.equals(cookie.getName())) {
//				obj = ";" + Apikey.APIKEY_PREFIX + request.getHeader(APIKEY_HEADER);
//			}else if (JWT_HEADER.equals(cookie.getName())) {
//				obj = ";" +  Jwt.JWT_PREFIX + request.getHeader(JWT_HEADER);
//			}
//		}
//		return obj != null ? obj.toString().substring(1) : obj;
//	}

	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
		return null;
	}

}
