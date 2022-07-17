package com.github.d33d4y0.training.mvc.authfilter.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.github.d33d4y0.training.mvc.authfilter.domain.Apikey;
import com.github.d33d4y0.training.mvc.authfilter.domain.AuthorizedPath;
import com.github.d33d4y0.training.mvc.authfilter.domain.Jwt;
import com.github.d33d4y0.training.mvc.authfilter.domain.Path;
import com.github.d33d4y0.training.mvc.authfilter.filter.AuthFilter;

@Configuration
public class AuthorizedConfig extends WebSecurityConfigurerAdapter {

	private static final String ROLE_PREFIX = "ROLE_";
	@Autowired
	private AuthFilter authFilter;

	@Autowired
	private AuthConfig authConfig;

//	Dynamically authorize path from configuration
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		Map<String, AuthorizedPath> authorizedPath = new HashMap<>();
		String key;
//		These for loop execute only starting time
		for (Entry<String, Apikey> each : authConfig.getApikeys().entrySet()) {
			for (Path path : each.getValue().getPaths()) {
				for (String method : path.getMethods()) {
					key = method + path.getPath();
					if (authorizedPath.get(key) == null) {
						authorizedPath.put(key, new AuthorizedPath(path.getPath(), HttpMethod.resolve(method)));
					}
					authorizedPath.get(key).getRoles()
							.add(ROLE_PREFIX + Apikey.APIKEY_PREFIX + each.getValue().getRole());
				}
			}
		}
		for (Entry<String, Jwt> each : authConfig.getJwts().entrySet()) {
			for (Path path : each.getValue().getPaths()) {
				for (String method : path.getMethods()) {
					key = method + path.getPath();
					if (authorizedPath.get(key) == null) {
						authorizedPath.put(key, new AuthorizedPath(path.getPath(), HttpMethod.resolve(method)));
					}
					authorizedPath.get(key).getRoles().add(ROLE_PREFIX + Jwt.JWT_PREFIX + each.getKey().toString());
				}
			}
		}
		ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.httpBasic()
				.and().antMatcher("/secure/**").csrf().disable().cors().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().formLogin().disable()
				.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class).authorizeRequests();
		for (Entry<String, AuthorizedPath> each : authorizedPath.entrySet()) {
			registry.antMatchers(each.getValue().getMethod(), each.getValue().getPath())
					.hasAnyAuthority(each.getValue().getRoles().toArray(new String[0]));
		}
		registry.anyRequest().authenticated();
	}

//	@Bean
//	public CorsConfigurationSource corsConfigurationSource() {
//		CorsConfiguration configuration = new CorsConfiguration();
//		configuration.setAllowedOrigins(Arrays.asList("*"));
//		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
//		configuration
//				.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token", "apikey", "env"));
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", configuration);
//		return source;
//	}
}
