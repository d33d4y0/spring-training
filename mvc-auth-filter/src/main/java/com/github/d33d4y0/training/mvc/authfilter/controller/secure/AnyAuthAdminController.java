package com.github.d33d4y0.training.mvc.authfilter.controller.secure;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secure/admin")
public class AnyAuthAdminController {

	@GetMapping()
	public String admin(@RequestHeader(value = "apikey", required = false) String apikey,
			@RequestHeader(value = "jwt", required = false) String jwt) {
		return "Authenticated";
	}
}
