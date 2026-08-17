package com.example.ems1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ems1.dto.RegisterRequest;
import com.example.ems1.service.OtpService;
import com.example.ems1.dto.VerifyOtpRequest;
import com.example.ems1.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	
	private UserService userService;
	private OtpService otpService;


	public UserController(UserService userService, OtpService otpService) {
		
		this.userService = userService;
		this.otpService = otpService;
	}


	@PostMapping("/register")
	public String userRegister(@RequestBody RegisterRequest registerRequest) {
		return userService.register(registerRequest);
	}
	
	@PostMapping("/verify-otp")
	public String VerifyOtp(@RequestBody VerifyOtpRequest verifyOtpRequest) {
		return	otpService.VerifyOtp(verifyOtpRequest);
	}
	
	@PostMapping("/resend-otp")
	public String resendOtp(@RequestBody VerifyOtpRequest verifyOtpRequest) {
		return userService.resendOtp(verifyOtpRequest);
	}
}
