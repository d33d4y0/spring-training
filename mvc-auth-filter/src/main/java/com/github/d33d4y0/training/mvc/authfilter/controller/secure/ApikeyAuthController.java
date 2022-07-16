package com.github.d33d4y0.training.mvc.authfilter.controller.secure;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secure/apikey")
public class ApikeyAuthController {

	@GetMapping()
	public String apikeyUser(@RequestHeader(value = "apikey", required = false) String apikey,
			@RequestHeader(value = "jwt", required = false) String jwt) {
		return "Authenticated";
	}

	@GetMapping("/admin/1")
	public String apikeyAdmin1(@RequestHeader(value = "apikey", required = false) String apikey,
			@RequestHeader(value = "jwt", required = false) String jwt) {
		return "Authenticated";
	}

	@GetMapping("/admin/2")
	public String apikeyAdmin2(@RequestHeader(value = "apikey", required = false) String apikey,
			@RequestHeader(value = "jwt", required = false) String jwt) {
		return "Authenticated";
	}
}
