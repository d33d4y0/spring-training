package com.github.d33d4y0.training.mvc.authfilter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.github.d33d4y0.training.mvc.authfilter.filter.AuthFilter;

@Configuration
public class AuthorizedConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private AuthFilter authFilter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and().antMatcher("/secure/**")
				.csrf().disable().cors()
				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and().formLogin().disable()
				.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class).authorizeRequests()
				.antMatchers(HttpMethod.GET, "/secure/admin/**")
				.hasAnyAuthority("ROLE_JWT_ADMIN-1", "ROLE_JWT_ADMIN-2", "ROLE_APIKEY_ADMIN-1", "ROLE_APIKEY_ADMIN-2")
				.antMatchers(HttpMethod.PUT, "/secure/admin/**")
				.hasAnyAuthority("ROLE_JWT_ADMIN-1", "ROLE_JWT_ADMIN-2", "ROLE_APIKEY_ADMIN-1", "ROLE_APIKEY_ADMIN-2")
				.antMatchers(HttpMethod.POST, "/secure/admin/**")
				.hasAnyAuthority("ROLE_JWT_ADMIN-1", "ROLE_JWT_ADMIN-2", "ROLE_APIKEY_ADMIN-1", "ROLE_APIKEY_ADMIN-2")
				.antMatchers(HttpMethod.DELETE, "/secure/admin/**")
				.hasAnyAuthority("ROLE_JWT_ADMIN-1", "ROLE_JWT_ADMIN-2", "ROLE_APIKEY_ADMIN-1", "ROLE_APIKEY_ADMIN-2")

				.antMatchers(HttpMethod.GET, "/secure/jwt/admin/1/**").hasAnyAuthority("ROLE_JWT_ADMIN-1")
				.antMatchers(HttpMethod.PUT, "/secure/jwt/admin/1/**").hasAnyAuthority("ROLE_JWT_ADMIN-1")
				.antMatchers(HttpMethod.POST, "/secure/jwt/admin/1/**").hasAnyAuthority("ROLE_JWT_ADMIN-1")
				.antMatchers(HttpMethod.DELETE, "/secure/jwt/admin/1/**").hasAnyAuthority("ROLE_JWT_ADMIN-1")

				.antMatchers(HttpMethod.GET, "/secure/jwt/admin/2/**").hasAnyAuthority("ROLE_JWT_ADMIN-2")
				.antMatchers(HttpMethod.PUT, "/secure/jwt/admin/2/**").hasAnyAuthority("ROLE_JWT_ADMIN-2")
				.antMatchers(HttpMethod.POST, "/secure/jwt/admin/2/**").hasAnyAuthority("ROLE_JWT_ADMIN-2")
				.antMatchers(HttpMethod.DELETE, "/secure/jwt/admin/2/**").hasAnyAuthority("ROLE_JWT_ADMIN-2")

				.antMatchers(HttpMethod.GET, "/secure/jwt/**")
				.hasAnyAuthority("ROLE_JWT_USER", "ROLE_JWT_ADMIN-1", "ROLE_JWT_ADMIN-2")
				.antMatchers(HttpMethod.PUT, "/secure/jwt/**")
				.hasAnyAuthority("ROLE_JWT_USER", "ROLE_JWT_ADMIN-1", "ROLE_JWT_ADMIN-2")
				.antMatchers(HttpMethod.POST, "/secure/jwt/**")
				.hasAnyAuthority("ROLE_JWT_USER", "ROLE_JWT_ADMIN-1", "ROLE_JWT_ADMIN-2")
				.antMatchers(HttpMethod.DELETE, "/secure/jwt/**")
				.hasAnyAuthority("ROLE_JWT_USER", "ROLE_JWT_ADMIN-1", "ROLE_JWT_ADMIN-2")

				.antMatchers(HttpMethod.GET, "/secure/apikey/admin/1/**").hasAnyAuthority("ROLE_APIKEY_ADMIN-1")
				.antMatchers(HttpMethod.PUT, "/secure/apikey/admin/1/**").hasAnyAuthority("ROLE_APIKEY_ADMIN-1")
				.antMatchers(HttpMethod.POST, "/secure/apikey/admin/1/**").hasAnyAuthority("ROLE_APIKEY_ADMIN-1")
				.antMatchers(HttpMethod.DELETE, "/secure/apikey/admin/1/**").hasAnyAuthority("ROLE_APIKEY_ADMIN-1")

				.antMatchers(HttpMethod.GET, "/secure/apikey/admin/2/**").hasAnyAuthority("ROLE_APIKEY_ADMIN-2")
				.antMatchers(HttpMethod.PUT, "/secure/apikey/admin/2/**").hasAnyAuthority("ROLE_APIKEY_ADMIN-2")
				.antMatchers(HttpMethod.POST, "/secure/apikey/admin/2/**").hasAnyAuthority("ROLE_APIKEY_ADMIN-2")
				.antMatchers(HttpMethod.DELETE, "/secure/apikey/admin/2/**").hasAnyAuthority("ROLE_APIKEY_ADMIN-2")

				.antMatchers(HttpMethod.GET, "/secure/apikey/**")
				.hasAnyAuthority("ROLE_APIKEY_USER", "ROLE_APIKEY_ADMIN-1", "ROLE_APIKEY_ADMIN-2")
				.antMatchers(HttpMethod.PUT, "/secure/apikey/**")
				.hasAnyAuthority("ROLE_APIKEY_USER", "ROLE_APIKEY_ADMIN-1", "ROLE_APIKEY_ADMIN-2")
				.antMatchers(HttpMethod.POST, "/secure/apikey/**")
				.hasAnyAuthority("ROLE_APIKEY_USER", "ROLE_APIKEY_ADMIN-1", "ROLE_APIKEY_ADMIN-2")
				.antMatchers(HttpMethod.DELETE, "/secure/apikey/**")
				.hasAnyAuthority("ROLE_APIKEY_USER", "ROLE_APIKEY_ADMIN-1", "ROLE_APIKEY_ADMIN-2")
				// Admin GET
//				.antMatchers(HttpMethod.GET, "/secure/ADMIN/**").hasAnyRole("JWT_ADMIN", "APIKEY_ADMIN")
//				// Admin POST
//				.antMatchers(HttpMethod.POST, "/secure/ADMIN/**").hasAnyRole("JWT_ADMIN", "APIKEY_ADMIN")
//				// Admin PUT
//				.antMatchers(HttpMethod.PUT, "/secure/ADMIN/**").hasAnyRole("JWT_ADMIN", "APIKEY_ADMIN")
//				// Admin DELETE
//				.antMatchers(HttpMethod.DELETE, "/secure/ADMIN/**").hasAnyRole("JWT_ADMIN", "APIKEY_ADMIN")
//				// Secure GET
//				.antMatchers(HttpMethod.GET, "/secure/**")
//				.hasAnyRole("JWT_ADMIN", "JWT_USER", "APIKEY_ADMIN", "APIKEY_USER")
//				// Secure POST
//				.antMatchers(HttpMethod.POST, "/secure/**")
//				.hasAnyRole("JWT_ADMIN", "JWT_USER", "APIKEY_ADMIN", "APIKEY_USER")
//				// Secure PUT
//				.antMatchers(HttpMethod.PUT, "/secure/**")
//				.hasAnyRole("JWT_ADMIN", "JWT_USER", "APIKEY_ADMIN", "APIKEY_USER")
//				// Secure DELETE
//				.antMatchers(HttpMethod.DELETE, "/secure/**")
//				.hasAnyRole("JWT_ADMIN", "JWT_USER", "APIKEY_ADMIN", "APIKEY_USER")
				// Authenticate
				.anyRequest().authenticated();
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
