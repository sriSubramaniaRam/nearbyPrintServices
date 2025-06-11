package io.github.sriSubramaniaRam.printer_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.sriSubramaniaRam.printer_service.service.AuthService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping("/send-otp")
	public Mono<String> sendOtp(@RequestParam String email) {
		return authService.sendOtpEmail(email).map(success -> success ? "OTP sent successfully" : "Failed to send OTP");
	}
}
