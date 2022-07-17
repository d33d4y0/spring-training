package com.github.d33d4y0.training.mvc.authfilter.config;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.github.d33d4y0.training.mvc.authfilter.domain.Apikey;
import com.github.d33d4y0.training.mvc.authfilter.domain.AuthorizedPath;
import com.github.d33d4y0.training.mvc.authfilter.domain.Jwt;
import com.github.d33d4y0.training.mvc.authfilter.filter.AuthFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String ROLE_PREFIX = "ROLE_";
	@Autowired
	private AuthFilter authFilter;
	@Autowired
	private AuthorizedConfig authorizedConfig;

	private static final JsonMapper JSON_MAPPER = JsonMapper.builder()
			.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
			.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true)
			.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true).serializationInclusion(Include.ALWAYS)
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).build();

	static {
		JSON_MAPPER.findAndRegisterModules();
	}

//	Dynamically authorize path from configuration
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.httpBasic()
				.and().antMatcher("/secure/**").csrf().disable().cors().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().formLogin().disable()
				.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class).authorizeRequests();
		for (AuthorizedPath each : authorizedConfig.getAuthorized()) {
			for (String method : each.getMethods()) {
				registry.antMatchers(HttpMethod.resolve(method), each.getPath()).hasAnyAuthority(
						convertAndCombineAuthorities(each.getApikeyUser(), each.getJwtUser()).toArray(new String[0]));
			}
		}
		registry.anyRequest().authenticated();
	}

	private List<String> convertAndCombineAuthorities(List<String> apikeyUsers, List<String> jwtUsers) {
		List<String> combined = new ArrayList<>();
		if (apikeyUsers != null) {
			apikeyUsers = apikeyUsers.stream().map(user -> user = ROLE_PREFIX + Apikey.APIKEY_PREFIX + user)
					.collect(Collectors.toList());
			combined.addAll(apikeyUsers);
		}
		if (jwtUsers != null) {
			jwtUsers = jwtUsers.stream().map(user -> user = ROLE_PREFIX + Jwt.JWT_PREFIX + user)
					.collect(Collectors.toList());
			combined.addAll(jwtUsers);
		}
		try {
			System.out.println(JSON_MAPPER.writeValueAsString(combined));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return combined;
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
