package com.github.d33d4y0.training.mvc.authfilter.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.github.d33d4y0.training.mvc.authfilter.config.ApikeyConfig;
import com.github.d33d4y0.training.mvc.authfilter.domain.Apikey;
import com.github.d33d4y0.training.mvc.authfilter.domain.Jwt;
import com.github.d33d4y0.training.mvc.authfilter.service.JwtService;

import io.jsonwebtoken.Claims;

@Component
public class AuthManager implements AuthenticationManager {

	@Autowired
	private ApikeyConfig apikeyConfig;

	private static Map<String, Apikey> APIKEYS;

	@PostConstruct
	private void init() {
		APIKEYS = apikeyConfig.getApikeys();
	}

	@Override
	public Authentication authenticate(Authentication preAuthentication) throws AuthenticationException {
		Authentication authentication = null;
		String[] principals = preAuthentication.getPrincipal().toString().isEmpty() ? null
				: preAuthentication.getPrincipal().toString().split(";");
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		if (principals == null) {
			authorities.add(new SimpleGrantedAuthority("ROLE_EMPTY"));
		} else {
			for (String principal : principals) {
				if (principal.contains(Apikey.APIKEY_PREFIX)) {
					principal = principal.substring(Apikey.APIKEY_PREFIX.length(), principal.length());
					Apikey apikey = APIKEYS.get(principal);
					if (apikey != null) {
						authorities.add(new SimpleGrantedAuthority("ROLE_" + Apikey.APIKEY_PREFIX + apikey.getRole()));
					}
				} else if (principal.contains(Jwt.JWT_PREFIX)) {
					principal = principal.substring(Jwt.JWT_PREFIX.length(), principal.length());
					Claims claims = JwtService.extractAllClaims(principal);
					String role = (String) claims.get("role");
					if (role != null) {
						authorities.add(new SimpleGrantedAuthority("ROLE_" + Jwt.JWT_PREFIX + role));
					}
				}
			}
		}
		preAuthentication.setAuthenticated(true);
		authentication = new UsernamePasswordAuthenticationToken(null, null, authorities);
		return authentication;
	}

}
