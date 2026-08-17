package com.example.ems1.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ems1.dto.RegisterRequest;
import com.example.ems1.dto.VerifyOtpRequest;
import com.example.ems1.entity.User;
import com.example.ems1.repository.UserRepository;

import com.example.ems1.util.OtpGenerator;

@Service
public class UserService {
	
	private UserRepository userRepository;
		
	private EmailService emailService;
	
	private PasswordEncoder passwordEncoder;
	

	public UserService(UserRepository userRepository, EmailService emailService, PasswordEncoder passwordEncoder) {

		this.userRepository = userRepository;
		this.emailService = emailService;
		this.passwordEncoder = passwordEncoder;
	}



	public String register(RegisterRequest registerRequest) {
		
		Optional<User> ou= userRepository.getByEmail(registerRequest.getEmail());
		if(ou.isPresent()) {
			return "The Email Is Already Exists Please Enter The Unique Email";
		}else {
			User user=new User();
			user.setName(registerRequest.getName());
			user.setEmail(registerRequest.getEmail());
			user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
			user.setRole("USER_ROLE");
			user.setVerified(false);
			
			String otp=OtpGenerator.generateOtp();
			
			user.setOtp(otp);
			user.setOtpexpirytime(LocalDateTime.now().plusMinutes(1));     // like it mention that otp expirs in next 10 min of geneartion
			
			userRepository.save(user);
			
			
			emailService.sendotp(registerRequest.getEmail(),otp);
			
			return "Please Enter The Otp For Verification";
		}
	}
	public String resendOtp(VerifyOtpRequest verifyOtpRequest) {
		Optional<User> ou = userRepository.getByEmail(verifyOtpRequest.getEmail());
		
		if(ou.isEmpty()) { 
			return "The email does not exist. Please register first.";
		} 
		
		User user = ou.get();
		String otp = OtpGenerator.generateOtp();
		
		user.setOtp(otp);
		user.setOtpexpirytime(LocalDateTime.now().plusMinutes(10)); 
		
		userRepository.save(user);
		emailService.sendotp(verifyOtpRequest.getEmail(), otp);
		
		return "A new OTP has been sent. Please enter the OTP for verification.";
	}

		
}



	
	
	

