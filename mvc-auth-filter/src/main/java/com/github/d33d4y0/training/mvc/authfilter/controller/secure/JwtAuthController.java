package com.github.d33d4y0.training.mvc.authfilter.controller.secure;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secure/jwt")
public class JwtAuthController {

	@GetMapping()
	public String jwtUser(@RequestHeader(value = "jwt", required = false) String jwt,
			@RequestHeader(value = "apikey", required = false) String apikey) {
		return "Authenticated";
	}

	@GetMapping("/admin/1")
	public String jwtAdmin1(@RequestHeader(value = "jwt", required = false) String jwt,
			@RequestHeader(value = "apikey", required = false) String apikey) {
		return "Authenticated";
	}

	@GetMapping("/admin/2")
	public String jwtAdmin2(@RequestHeader(value = "jwt", required = false) String jwt,
			@RequestHeader(value = "apikey", required = false) String apikey) {
		return "Authenticated";
	}
}
